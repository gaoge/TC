package com.feng.android.third_framework.retrofit;

import com.feng.android.third_framework.retrofit.v2.Result;
import com.feng.android.third_framework.together.v2.ErrorHandle;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Emitter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

import static com.feng.android.third_framework.retrofit.ssl.TCSSLSocketFactory.getTrustAllSockeFactory;
import static com.feng.android.third_framework.retrofit.ssl.TCSSLSocketFactory.getTrustManager;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 14:47
 * @tips
 */
public class RetrofitClient {
    private final static ServiceApi mServiceApi;

    static {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //1. 没打印?
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(@NotNull String s) {
                        Timber.i(s);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .sslSocketFactory(getTrustAllSockeFactory(),getTrustManager())
                .build();
        //各种套路和格式，发现问题解决问题，基础，源码的理解

        //2. 数据格式不一致？成功data 是个对象;不成功data 是个 String
        //3. baseUrl 问题？ (Retrofit找不到任何入口可以修改)
        //   3.1 不同的baseUrl ，构建不同的 Retrofit对象 (直，不应该首选)
        //   3.2 自己想办法，取巧也行，走漏洞
        Retrofit retrofit = new Retrofit.Builder()
                //访问后台接口的主路径
                .baseUrl("https://update.fengjr.com/")
                //添加解析转换工厂,Gson 解析,Xml解析，等等
                .addConverterFactory(GsonConverterFactory.create())
                //添加OkHttpClient
                //添加 OkHttpClient,不添加默认就是 光杆 OkHttpClient
                .client(okHttpClient)
                //支持 Rxjava Call -> Observable,怎么做不到的？ 采用了 Adapter设计模式
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        //创建一个实例对象
        mServiceApi = retrofit.create(ServiceApi.class);
    }



    public static ServiceApi getServiceApi() {
        return mServiceApi;
    }

    public static <T> Observable.Transformer<Result<T>,T> transformer() {
        return new Observable.Transformer<Result<T>, T>() {
            @Override
            public Observable<T> call(Observable<Result<T>> resultObservable) {
                //resultObservable 这个地方，相当于ServiceApi里的 Observable<Result<UserInfo>> userLoginV4()
                return resultObservable.flatMap(new Func1<Result<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(Result<T> tResult) {
                        //解析不同的情况返回
                        if(tResult.isOk()){
                            //返回成功
                            return createObservale(tResult.data);
                        }else{
                            //返回失败
                            Observable.error(new ErrorHandle.ServerError("",tResult.getMsg()));
                        }
                        return null;
                    }
                })
                        .onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
                                @Override
                                public Observable<? extends T> call(Throwable throwable) {
                                    return Observable.error(new ErrorHandle.HttpError(throwable));
                                }
                        })
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private static <T> Observable<T> createObservale(T data) {
        return Observable.create(new Action1<Emitter<T>>() {
            @Override
            public void call(Emitter<T> tEmitter) {
                tEmitter.onNext(data);
                tEmitter.onCompleted();
            }
        },Emitter.BackpressureMode.NONE);
    }
}

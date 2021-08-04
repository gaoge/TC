package com.feng.android.net.engine.retrofit;

import com.feng.android.net.ssl.TrustAllSslSocketFactory;


import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

import timber.log.Timber;



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
                .sslSocketFactory(TrustAllSslSocketFactory.getTrustAllSockeFactory(),TrustAllSslSocketFactory.getTrustManager())
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
                //不要转换工厂(但是加上结果也正常)，默认的情况下返回的是 ResponseBody,
                // 有一个方面是可以去切换解析工厂
//                .addConverterFactory(GsonConverterFactory.create())
                //添加OkHttpClient
                //添加 OkHttpClient,不添加默认就是 光杆 OkHttpClient
                .client(okHttpClient)
                .build();

        //创建一个实例对象
        mServiceApi = retrofit.create(ServiceApi.class);
    }



    public static ServiceApi getServiceApi() {
        return mServiceApi;
    }


}

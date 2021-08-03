package com.feng.android.third_framework.retrofit.my;

import org.jetbrains.annotations.NotNull;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
                        Timber.e(s);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                //访问后台接口的主路径
                .baseUrl("http://10.253.123.174:8080/OkHttpServer/")
                //添加解析转换工厂,Gson 解析,Xml解析，等等
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

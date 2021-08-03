package com.feng.android.third_framework.retrofit.my;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;


import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-03 09:40
 * @tips
 */
public class OkHttpCall<T> implements Call<T> {
    final ServiceMethod serviceMethod;
    final Object[] args;
    public OkHttpCall(ServiceMethod serviceMethod, Object[] args) {
        this.serviceMethod = serviceMethod;
        this.args = args;
    }

    @Override
    public void enqueue(Callback<T> callback) {
        //发起一个请求，给一个回调 就完了
        Timber.e("正式发起请求");
        okhttp3.Call newCall = serviceMethod.createNewCall(args);
        newCall.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.@NotNull Call call, @NotNull IOException e) {
                if(null != callback){
                    callback.onFailure(OkHttpCall.this::enqueue,e);
                }
            }

            @Override
            public void onResponse(okhttp3.@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
                //解析 Resposne -> Response<T> 回调
                Timber.e(response.body().toString());
                //涉及到解析，不能在这里写死 ，ConvertFactory
                Response response1 = new Response();
                response1.body = serviceMethod.parseBody(response.body());

                if(null != callback){
                    callback.onResponse(OkHttpCall.this,response1);
                }
            }
        });


    }
}

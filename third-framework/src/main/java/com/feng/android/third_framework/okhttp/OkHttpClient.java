package com.feng.android.third_framework.okhttp;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-27 11:54
 * @tips
 */
public class OkHttpClient {

    final Dispatcher dispatcher;
    private OkHttpClient(Builder builder) {
        this.dispatcher = builder.dispatcher;
    }

    private OkHttpClient(){
        this(new Builder());
    }

    public Call newCall(Request request){
        return RealCall.newCall(request,this);
    }

    public static class Builder{
        Dispatcher dispatcher;
        //连接超时
        //https 证书的一些参数
        //拦截器
        //等等
        public Builder(){
            dispatcher = new Dispatcher();
        }

        public OkHttpClient build(){
            return new OkHttpClient(this);
        }
    }
}

package com.feng.android.third_framework.okhttp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-27 11:54
 * @tips
 */
public class Request {
    final String url;
    public final Method method;
    final Map<String,String> headers;
    final RequestBody requestBody;

    public Request(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers;
        this.requestBody = builder.requestBody;
    }

    public void header(String key, String value) {
        headers.put(key,value);
    }

    public RequestBody requestBody(){
        return requestBody;
    }

    public String url(){
        return url;
    }

    public static class Builder{
        public RequestBody requestBody;
        //String url,Body post参数，请求头
        String url;
        Method method;
        Map<String,String> headers;

        public Builder(){
            method = Method.GET;
            headers = new HashMap<>();
        }

        public Request build(){
            return new Request(this);
        }
        public Builder url(String url){
            this.url = url;
            return this;
        }

        public Builder get(){
            method = Method.GET;
            return this;
        }

        public Builder post(RequestBody body){
            method = Method.POST;
            this.requestBody = body;
            return this;
        }

        public void header(String key,String value){
            headers.put(key,value);
        }
    }
}

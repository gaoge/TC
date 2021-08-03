package com.feng.android.third_framework.retrofit.my;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-03 10:25
 * @tips
 */
public class RequestBuilder {
    ParameterHandler<Object>[] parameterHandlers;
    Object[] args;
    HttpUrl.Builder httpUrlBuilder;

    public RequestBuilder(String baseUrl, String relativeUrl, String httpMethod, ParameterHandler<?>[] parameterHandlers,Object[] args) {
        this.parameterHandlers = (ParameterHandler<Object>[]) parameterHandlers;
        this.args = args;
        httpUrlBuilder = HttpUrl.parse(baseUrl+relativeUrl).newBuilder();
    }

    public Request build() {
        int count = args.length;
        for(int i =0;i<count;i++){
            //userName = Darren
            parameterHandlers[i].apply(this,args[i]);
        }

        //POST 等等完善
        Request request = new Request.Builder()
                .url(httpUrlBuilder.build())
                .build();

        return request;
    }

    public void addQueryName(String key, String value) {
        //userName = Darren, password = 123456
        httpUrlBuilder.addQueryParameter(key,value);
    }
}

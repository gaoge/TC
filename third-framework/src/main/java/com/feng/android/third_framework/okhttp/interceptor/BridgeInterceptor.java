package com.feng.android.third_framework.okhttp.interceptor;

import com.feng.android.third_framework.okhttp.Request;
import com.feng.android.third_framework.okhttp.RequestBody;
import com.feng.android.third_framework.okhttp.Response;

import java.io.IOException;

import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-28 10:15
 * @tips
 */
public class BridgeInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Timber.e("intercept()");
        Request request = chain.request();
        //添加一些请求头
        request.header("Connection","keep-alive");
        //做一些其它处理
        if(request.requestBody() != null){
            RequestBody requestBody = request.requestBody();
            request.header("Content-Type",requestBody.getContentType());
            request.header("Content-Length",Long.toString(requestBody.getContentLength()));

        }
        Response response = chain.proceed(request);
        return response;
    }
}

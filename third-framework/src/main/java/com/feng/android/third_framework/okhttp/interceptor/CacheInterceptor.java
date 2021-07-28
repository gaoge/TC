package com.feng.android.third_framework.okhttp.interceptor;

import com.feng.android.third_framework.okhttp.Request;
import com.feng.android.third_framework.okhttp.Response;

import java.io.IOException;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-28 11:04
 * @tips
 */
public class CacheInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //本地没有缓存，如果有没国旗
//        if(true){
//            return new Response();
//        }
        return chain.proceed(request);
    }
}

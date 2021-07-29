package com.feng.android.third_framework.okhttp.cache;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-28 17:37
 * @tips
 */
public class CacheResponseInteceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        //过期时间是30s
        response = response.newBuilder()
                .removeHeader("Cache-Control")
                .removeHeader("Pragma")
                .addHeader("Cache-control","max-age=" + 30)
                .build();

        return response;
    }
}

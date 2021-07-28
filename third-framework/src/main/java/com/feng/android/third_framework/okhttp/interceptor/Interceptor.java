package com.feng.android.third_framework.okhttp.interceptor;

import com.feng.android.third_framework.okhttp.Request;
import com.feng.android.third_framework.okhttp.Response;

import java.io.IOException;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-28 10:15
 * @tips
 */
public interface Interceptor {
    Response intercept(Chain chain) throws IOException;

    interface Chain{
        Request request();
        Response proceed(Request request) throws IOException;
    }
}

package com.feng.android.third_framework.okhttp;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-27 11:53
 * @tips
 */
public interface Call {
    void enqueue(Callback callback);

    Response execute();
}

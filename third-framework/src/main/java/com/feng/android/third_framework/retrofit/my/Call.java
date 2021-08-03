package com.feng.android.third_framework.retrofit.my;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 17:44
 * @tips
 */
public interface Call<T> {
    void enqueue(Callback<T> callback);
}

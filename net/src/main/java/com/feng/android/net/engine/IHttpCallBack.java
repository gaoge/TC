package com.feng.android.net.engine;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-04 11:57
 * @tips
 */
public interface IHttpCallBack<T> {
    void onFailure(Exception e);
    void onSuccess(T object);
}

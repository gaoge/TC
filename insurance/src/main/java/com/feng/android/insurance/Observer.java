package com.feng.android.insurance;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 17:50
 * @tips
 */
public interface Observer<T> {
    void update(T t);
}

package com.feng.android.third_framework.rxjava;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-30 14:24
 * @tips
 */
public interface ObservableSource<T> {
    void subscribe(Observer<T> observer);
}

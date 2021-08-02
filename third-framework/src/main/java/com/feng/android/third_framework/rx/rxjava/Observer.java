package com.feng.android.third_framework.rx.rxjava;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-30 14:24
 * @tips
 * 观察者
 */
public interface Observer<T> {
    void onSubscribe();
    void onNext(T item);
    void onError(Throwable e);
    void onComplete();
}

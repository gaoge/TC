package com.feng.android.third_framework.rx.rxjava;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-30 15:46
 * @tips
 */
public interface Function<T,R> {
    R apply(T t) throws Exception;
}

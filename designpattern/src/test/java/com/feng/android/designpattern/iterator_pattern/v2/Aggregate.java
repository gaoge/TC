package com.feng.android.designpattern.iterator_pattern.v2;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 17:13
 * @tips
 * 容器的接口
 */
public interface Aggregate<T> {
    Iterator<T> iterator();
}

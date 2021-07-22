package com.feng.android.designpattern.iterator_pattern.v2;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 17:09
 * @tips
 * 迭代器的接口
 */
public interface Iterator<T> {

    T next();
    boolean hasNext();
}

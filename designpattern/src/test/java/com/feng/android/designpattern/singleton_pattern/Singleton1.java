package com.feng.android.designpattern.singleton_pattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 16:14
 * @tips
 * 单例-饿汉式
 */
public class Singleton1 {
    //随着类的加载就已经new 了对象
    private static Singleton1 mInstance = new Singleton1();

    private Singleton1(){

    }

    public static Singleton1 getInstance(){
        return mInstance;
    }
}

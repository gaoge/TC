package com.feng.android.designpattern.singleton;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 17:00
 * @tips
 * 单例模式 - 静态内部类(比较常用)
 */
public class Singleton3 {

    public static Singleton3 getInstance(){
        return SingletonHolder.mInstance;
    }
    private Singleton3(){

    }

    public static class SingletonHolder{
        private static volatile Singleton3 mInstance = new Singleton3();
    }
}

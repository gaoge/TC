package com.feng.android.tc.desingpattern.singleton;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 17:02
 * @tips
 * 单例设计模式 - 自定义
 */
public class Singleton5 {

    private static Singleton5 mInstance;

    static {
        mInstance = new Singleton5();
    }
    private Singleton5(){

    }

    public static Singleton5 getInstance(){
        return mInstance;
    }

}

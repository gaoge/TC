package com.feng.android.designpattern.singleton_pattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 16:14
 * @tips
 * 单例设计模式-懒汉式
 */
public class Singleton2Sync1 {
    //只有在使用的时候才会去new 对象，可能更加高效
    //但是会有一些问题？多线程并发的问题，如果是多线程，还是会存在多个实例
    private static Singleton2Sync1 mInstance;

    private Singleton2Sync1(){

    }

    //虽说解决了线程安全的问题，但是又会出现效率的问题，
    // 又会显得比较低，每次获取都要经过同步锁的判断
    public static synchronized Singleton2Sync1 getInstance(){
        if(null == mInstance){
            mInstance = new Singleton2Sync1();
        }
        return mInstance;
    }
}

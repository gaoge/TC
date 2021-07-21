package com.feng.android.designpattern.singletonpattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 16:14
 * @tips
 * 单例设计模式-懒汉式
 */
public class Singleton2Sync2 {
    //只有在使用的时候才会去new 对象，可能更加高效
    //但是会有一些问题？多线程并发的问题，如果是多线程，还是会存在多个实例
    private static Singleton2Sync2 mInstance;

    private Singleton2Sync2(){

    }


    //既保证线程的安全，同时效率也是比较高的
    //这种方式其实还是会有问题
    public static  Singleton2Sync2 getInstance(){
        if(null == mInstance){//thread1,thread2
            synchronized (Singleton2Sync2.class){
                if(null == mInstance){
                    mInstance = new Singleton2Sync2();
                }
            }
        }
        return mInstance;
    }
}

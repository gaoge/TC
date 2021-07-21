package com.feng.android.designpattern.singletonpattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 16:14
 * @tips
 * 单例设计模式-懒汉式(DCL)
 */
public class Singleton2Sync3 {
    //加上volatile的用处是什么？
    //1.防止重排序
    //2.线程可见性
    private static volatile Singleton2Sync3 mInstance;

    private Singleton2Sync3(){

    }


    //既保证线程的安全，同时效率也是比较高的
    //这种方式其实还是会有问题
    public static Singleton2Sync3 getInstance(){
        if(null == mInstance){//thread1,thread2
            synchronized (Singleton2Sync3.class){
                if(null == mInstance){
                    mInstance = new Singleton2Sync3();
                }
            }
        }
        return mInstance;
    }
}

package com.feng.android.base.mvp;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 12:57
 * @tips
 */
public class BasePresenter<V extends BaseView> {
    //attach 传递的时候 会有不同的View,怎么办？ 泛型

    //强引用
//    private V mView;
    //弱引用
    private WeakReference<V> mViewWeakReference;
    private V mProxyView;
    //View 有一个特点
    //GC 回收的算法机制()
    //View 一般都是Activity,涉及到内存泄漏，但是已经解绑了，就不会泄漏
    //最好还是用一下软引用

    public void attach(V view){
        this.mViewWeakReference = new WeakReference<>(view);
        //用代理对象
        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(),
//                new Class[]{view.getClass()},
                //必须得是一个interface
                view.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //执行这个方法，调用的是被代理的对象
                        if(null != mViewWeakReference || mViewWeakReference.get() == null){
                            return method.invoke(mViewWeakReference.get(),args);
                        }
                        return null;
                    }
                });
    }

    //不解绑的问题: Activity -> Presenter, Presenter -> Activity；互相引用
    //所以这里不解绑 Activity会有内存泄漏
    public void detach(){
        this.mViewWeakReference.clear();
        this.mViewWeakReference = null;
        this.mProxyView = null;
    }

    public V getView() {
        //这里返回的代理对象
        return mProxyView;
    }
}

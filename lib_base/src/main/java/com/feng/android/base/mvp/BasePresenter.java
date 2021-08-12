package com.feng.android.base.mvp;

import com.feng.android.base.util.ReflectUtil;

import java.lang.ref.SoftReference;
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
public class BasePresenter<V extends BaseView,M extends BaseModel> {
    //attach 传递的时候 会有不同的View,怎么办？ 泛型

    //强引用
    //WeakReference 这个是一 GC 就被回收，可能出现 Activity 还在，但是无法更新UI的情况
    //SoftReference 会在 OOM 前，其实就是 Activity 被回收，这哥们也被回收
    //所以这里要用SoftReference,而不是 WeakReference
//    private V mView;
    //弱引用
    private SoftReference<V> mViewSoftReference;
    private V mProxyView;
    //View 有一个特点
    //GC 回收的算法机制()
    //View 一般都是Activity,涉及到内存泄漏（如果是可达性算法，不一定会泄漏，还有看P和V是否会被GCRoot引用到），但是已经解绑了，就不会泄漏
    //最好还是用一下软引用

    //要动态创建Model对象
    private M mModel;

    public void attach(V view){
        if(BaseView.class.isAssignableFrom(view.getClass())){
            throw new RuntimeException("MvpActivity must extends BaseView interface!");
        }
        this.mViewSoftReference = new SoftReference<>(view);
        //用代理对象
        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(),
//                new Class[]{view.getClass()},
                //必须得是一个interface
                view.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //执行这个方法，调用的是被代理的对象
                        if(null != mViewSoftReference.get() || mViewSoftReference.get() == null){
                            return method.invoke(mViewSoftReference.get(),args);
                        }
                        return null;
                    }
                });

        //创建我们的 Model, 动态创建? 获取 Class 通过反射，
        // (Activity 实例 就是通过反射创建)
        // 布局的View 也是通过反射创建
        //获取 Class 对象
        try {
            //最好判断一下类型
            //该类有两个泛型，所以Model 泛型对应的是第 1 个索引
            mModel = (M) ReflectUtil.getParameterType(this.getClass(),1).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    //不解绑的问题: Activity -> Presenter, Presenter -> Activity；互相引用
    //所以这里不解绑 Activity会有内存泄漏
    public void detach(){
        this.mViewSoftReference.clear();
        this.mViewSoftReference = null;
        this.mProxyView = null;
    }

    public V getView() {
        //这里返回的代理对象
        return mProxyView;
    }

    public M getModel() {
        return mModel;
    }
}

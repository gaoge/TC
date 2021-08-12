package com.feng.android.base.mvp;

import android.os.Bundle;

import com.feng.android.base.BaseActivity;
import com.feng.android.base.mvp.inject.InjectPresenter;
import com.feng.android.base.mvp.proxy.ActivityMvpProxy;
import com.feng.android.base.mvp.proxy.ActivityMvpProxyImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 13:09
 * @tips
 */
public abstract class BaseMVPActivity<P extends BasePresenter> extends BaseActivity implements BaseView{

    P mPresenter;
    private ActivityMvpProxy mActivityMvpProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //createPresenter() 为抽象模版，方法
        //1. mPresenter的具体类型在父类中无法确定，
        //2. 所以由子类实现该方法，结合泛型，从而返回确认的具体类型
        mPresenter = createPresenter();
        mPresenter.attach(this);



        //1. Activity ? 2. Fragment ? 3. ViewGroup 要抽离代码，工具类抽出去，
        //今天你可以抽离目前的一部分 注入代码 + 额外功能 + 还有一些其它地方又不一样
        mActivityMvpProxy = createMvpProxy();
        mActivityMvpProxy.bindAndCreatePresenter();
    }

    /**
     * 创建 Mvp 的代理，自己去写 BaseFragment
     * @return
     */
    protected  ActivityMvpProxy createMvpProxy(){
        if(null == mActivityMvpProxy){
            mActivityMvpProxy = new ActivityMvpProxyImpl<>(this);
        }
        return mActivityMvpProxy;
    }


    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivityMvpProxy.unbindPresenter();
        mPresenter.detach();

    }

    public P getPresenter() {
        return mPresenter;
    }
}

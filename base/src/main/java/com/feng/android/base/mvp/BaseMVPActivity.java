package com.feng.android.base.mvp;

import android.os.Bundle;

import com.feng.android.base.BaseActivity;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 13:09
 * @tips
 */
public abstract class BaseMVPActivity<P extends BasePresenter> extends BaseActivity implements BaseView{

    P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //createPresenter() 为抽象模版，方法
        //1. mPresenter的具体类型在父类中无法确定，
        //2. 所以由子类实现该方法，结合泛型，从而返回确认的具体类型
        mPresenter = createPresenter();
        mPresenter.attach(this);
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    public P getPresenter() {
        return mPresenter;
    }
}

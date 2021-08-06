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

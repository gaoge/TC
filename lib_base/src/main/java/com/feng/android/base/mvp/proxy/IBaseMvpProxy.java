package com.feng.android.base.mvp.proxy;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 17:21
 * @tips
 */
public interface IBaseMvpProxy {

    //绑定和创建
    void bindAndCreatePresenter();
    //解绑
    void unbindPresenter();
}

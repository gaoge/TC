package com.feng.android.base.mvp.proxy;

import com.feng.android.base.mvp.BaseView;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 17:31
 * @tips
 */
public class ActivityMvpProxyImpl<V extends BaseView> extends MvpProxyImpl<V> implements ActivityMvpProxy {

    public ActivityMvpProxyImpl(V view) {
        super(view);
    }
}

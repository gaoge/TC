package com.feng.android.designpattern.observerpattern.v2.pull.observer;

import com.feng.android.designpattern.observerpattern.v2.pull.observable.PullWXAdvanceObservable;
import com.feng.android.designpattern.observerpattern.v2.pull.observable.PullWXPublicObservable;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 16:01
 * @tips
 * 微信公众号-微信订阅用户
 */
public interface IPullWXuser {
     /**
      *
      * @param article
      */
     void pull(PullWXPublicObservable wxObservable);
}

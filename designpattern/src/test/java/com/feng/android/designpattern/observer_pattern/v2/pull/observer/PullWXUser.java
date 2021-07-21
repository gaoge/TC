package com.feng.android.designpattern.observer_pattern.v2.pull.observer;

import com.feng.android.designpattern.observer_pattern.v2.pull.observable.PullWXAdvanceObservable;
import com.feng.android.designpattern.observer_pattern.v2.pull.observable.PullWXPublicObservable;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 16:07
 * @tips
 * 微信公众号：具体订阅用户
 */
public class PullWXUser implements IPullWXuser {
    private String name;

    public PullWXUser(String name) {
        this.name = name;
    }


    @Override
    public void pull(PullWXPublicObservable wxObservable) {
        //拉模式 -主动的从公众号中拉去一篇文章
        PullWXAdvanceObservable advanceObservable = (PullWXAdvanceObservable)wxObservable;
        System.out.println(name + "主动拉取一篇文章: " + advanceObservable.getArticle());
    }
}

package com.feng.android.designpattern.observerpattern.v3.observable;

import java.util.Observable;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 16:05
 * @tips
 * 微信公众号：Android进阶之旅公众号
 */
public class WXAdvanceObservable extends Observable {

    private String article;

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
        //有更新
        setChanged();
        //更新文章，推送给微信用户
        notifyObservers(article);

    }
}

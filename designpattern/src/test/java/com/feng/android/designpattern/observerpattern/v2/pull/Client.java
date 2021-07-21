package com.feng.android.designpattern.observerpattern.v2.pull;

import com.feng.android.designpattern.observerpattern.v2.pull.observable.PullWXAdvanceObservable;
import com.feng.android.designpattern.observerpattern.v2.pull.observer.PullWXUser;

import org.junit.Test;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 16:09
 * @tips
 */
public class Client {

    @Test
    public void main(){
        //微信公众号-具体的被观察这-Android进阶之旅
        PullWXAdvanceObservable wxAdvanceObservable = new PullWXAdvanceObservable();

        //微信公众号-具体的观察者-tom
        PullWXUser tom = new PullWXUser("Tom");
        PullWXUser jack = new PullWXUser("jack");

        //用户订阅公众号
        wxAdvanceObservable.register(tom);
        wxAdvanceObservable.register(jack);

        //微信公众号推送文章
        wxAdvanceObservable.setArticle("《观察者模式详解》");

        //tom取消订阅
        wxAdvanceObservable.unregister(tom);

        wxAdvanceObservable.setArticle("《Retrofit动态代理详解》");
    }
}

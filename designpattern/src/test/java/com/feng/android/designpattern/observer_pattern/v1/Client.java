package com.feng.android.designpattern.observer_pattern.v1;

import com.feng.android.designpattern.observer_pattern.v1.observable.WXAdvanceObservable;
import com.feng.android.designpattern.observer_pattern.v1.observer.WXUser;

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
        WXAdvanceObservable wxAdvanceObservable = new WXAdvanceObservable();

        //微信公众号-具体的观察者-tom
        WXUser tom = new WXUser("Tom");
        WXUser jack = new WXUser("jack");

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

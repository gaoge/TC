package com.feng.android.designpattern.observer_pattern.v3.observer;

import com.feng.android.designpattern.observer_pattern.v3.observable.WXAdvanceObservable;

import java.util.Observable;
import java.util.Observer;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 16:07
 * @tips
 * 微信公众号：具体订阅用户
 *
 */
public class WXUser implements Observer {
    private String name;

    public WXUser(String name) {
        this.name = name;
    }

//    @Override
//    public void push(String article) {
//        System.out.println(name + "收到了一篇推送文章: " + article);
//    }

    //推拉模式，既可以拉，也可以推
    @Override
    public void update(Observable o, Object arg) {
        // o是具体的公众号，也就是WXAdavanceObservable
        //arg 是推送的文章article
        System.out.println(name + "收到了一篇推送文章: " + arg);

        WXAdvanceObservable advanceObservable = (WXAdvanceObservable)o;
        System.out.println(name + "主动拉去一篇文章: " + arg);


    }
}

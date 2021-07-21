package com.feng.android.designpattern.observer_pattern.v2.push.observer;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 16:07
 * @tips
 * 微信公众号：具体订阅用户
 */
public class PushWXUser implements IPushWXuser {
    private String name;

    public PushWXUser(String name) {
        this.name = name;
    }

    @Override
    public void push(String article) {
        System.out.println(name + "收到了一篇推送文章: " + article);
    }
}

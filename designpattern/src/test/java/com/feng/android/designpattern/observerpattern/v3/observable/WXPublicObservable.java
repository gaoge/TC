package com.feng.android.designpattern.observerpattern.v3.observable;

import com.feng.android.designpattern.observerpattern.v3.observer.IWXuser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 15:58
 * @tips
 * 微信公众号：多个人去订阅我们的公众号
 * 这个类不用了，用系统自带的Observable
 */
public class WXPublicObservable {
    //所有订阅的用户
    public List<IWXuser> mWXuser;

    public WXPublicObservable() {
        this.mWXuser = new ArrayList<>();
    }

    /**
     * 订阅
     */
    public void register(IWXuser iwXuser){
        mWXuser.add(iwXuser);
    }

    /**
     * 取消订阅
     */
    public void unregister(IWXuser iwXuser){
        mWXuser.remove(iwXuser);
    }

    /**
     * 更新文章
     * @param article
     */
    public void update(String article){
        //推送文章更新
        for(IWXuser iwXuser : mWXuser){
            iwXuser.push(article);
        }
    }
}

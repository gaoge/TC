package com.feng.android.designpattern.observerpattern.v2.push.observable;

import com.feng.android.designpattern.observerpattern.v2.push.observer.IPushWXuser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 15:58
 * @tips
 * 微信公众号：多个人去订阅我们的公众号
 */
public class PushWXPublicObservable {
    //所有订阅的用户
    public List<IPushWXuser> mWXuser;

    public PushWXPublicObservable() {
        this.mWXuser = new ArrayList<>();
    }

    /**
     * 订阅
     */
    public void register(IPushWXuser iwXuser){
        mWXuser.add(iwXuser);
    }

    /**
     * 取消订阅
     */
    public void unregister(IPushWXuser iwXuser){
        mWXuser.remove(iwXuser);
    }

    /**
     * 更新文章
     * @param article
     */
    public void update(String article){
        //推送文章更新
        for(IPushWXuser iwXuser : mWXuser){
            iwXuser.push(article);
        }
    }
}

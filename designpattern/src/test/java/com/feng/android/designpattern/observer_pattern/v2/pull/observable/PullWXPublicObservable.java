package com.feng.android.designpattern.observer_pattern.v2.pull.observable;

import com.feng.android.designpattern.observer_pattern.v2.pull.observer.IPullWXuser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 15:58
 * @tips
 * 微信公众号：多个人去订阅我们的公众号
 */
public class PullWXPublicObservable {
    //所有订阅的用户
    public List<IPullWXuser> mWXuser;

    public PullWXPublicObservable() {
        this.mWXuser = new ArrayList<>();
    }

    /**
     * 订阅
     */
    public void register(IPullWXuser iwXuser){
        mWXuser.add(iwXuser);
    }

    /**
     * 取消订阅
     */
    public void unregister(IPullWXuser iwXuser){
        mWXuser.remove(iwXuser);
    }

    /**
     * 更新文章
     * @param article
     */
    public void update(String article){
        //推送文章更新
        for(IPullWXuser iwXuser : mWXuser){
            iwXuser.pull(this);
        }
    }
}

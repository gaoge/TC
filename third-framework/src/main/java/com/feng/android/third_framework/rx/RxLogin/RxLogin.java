package com.feng.android.third_framework.rx.RxLogin;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.subjects.PublishSubject;


/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 11:44
 * @tips
 */
public class RxLogin implements PlatformActionListener {
    private Activity activity;
    static PlatformActionListener STATIC_LISTENER;
    private RxLoginResult mLoginResult;
    private PublishSubject<RxLoginResult> mEmitter;

    private RxLogin(Activity activity){
        this.activity = activity;
        STATIC_LISTENER = this;
        mLoginResult = new RxLoginResult();
        mEmitter = PublishSubject.create();
    }

    public static RxLogin create(Activity activity){
        return new RxLogin(activity);
    }

    public Observable<RxLoginResult> doOauthVerifty(RxLoginPlatform platformQq) {
        //开启一个透明的Activity,参照RxPermission 源码
        Intent intent = new Intent(activity,RxLoginActivity.class);
        intent.putExtra(RxLoginActivity.PLATFORM_KEY,platformQq);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);

        mLoginResult.setPlatform(platformQq);

        List<Observable<RxLoginResult>> list = new ArrayList<>(1);
        list.add(mEmitter);

        return Observable.concat(Observable.fromIterable(list));

    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        mLoginResult.setSucceed(true);
        mLoginResult.setUserInfoMaps(hashMap);
        mLoginResult.setMsg("获取用户信息成功");
        //拿到信息要回调回去 Activity -> Consumer.accept()方法
        mEmitter.onNext(mLoginResult);
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        mLoginResult.setSucceed(false);
        throwable.printStackTrace();
        mLoginResult.setMsg("获取用户信息失败");

        mEmitter.onNext(mLoginResult);

    }

    @Override
    public void onCancel(Platform platform, int i) {
        mLoginResult.setSucceed(false);
        mLoginResult.setMsg("用户取消第三方登录");

        mEmitter.onNext(mLoginResult);


    }
}

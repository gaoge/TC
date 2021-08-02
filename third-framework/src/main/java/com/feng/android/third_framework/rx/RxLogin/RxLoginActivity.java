package com.feng.android.third_framework.rx.RxLogin;

import android.app.Activity;
import android.os.Bundle;

import java.util.HashMap;

import androidx.annotation.Nullable;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 13:29
 * @tips
 */
public class RxLoginActivity extends Activity implements PlatformActionListener {
    public static final String PLATFORM_KEY = "platform_key";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RxLoginPlatform platform = (RxLoginPlatform) getIntent().getSerializableExtra(PLATFORM_KEY);

        //设置授权登录的平台
        Platform plat = ShareSDK.getPlatform(platformChange(platform));
        //移除授权状态和本地缓存，下次授权会重新授权
        plat.removeAccount(true);
        //SSO授权，传false默认是客户端授权
        plat.SSOSetting(false);
        //授权回调监听，监听oncomplete，onerror，oncancel三种状态
        plat.setPlatformActionListener(this);
        //抖音登录适配安卓9.0
        //ShareSDK.setActivity(MainActivity.this);
        plat.showUser(null);
    }

    /**
     * 平台转换
     * @param platform
     * @return
     */
    private String platformChange(RxLoginPlatform platform) {
        switch (platform){
            case PLATFORM_QQ:
                return Wechat.NAME;
            case PLATFORM_WX:
                return Wechat.NAME;
        }
        return Wechat.NAME;
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        //结果要传回去
        RxLogin.STATIC_LISTENER.onComplete(platform,i,hashMap);
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        RxLogin.STATIC_LISTENER.onError(platform,i,throwable);
    }

    @Override
    public void onCancel(Platform platform, int i) {
        RxLogin.STATIC_LISTENER.onCancel(platform,i);
    }
}

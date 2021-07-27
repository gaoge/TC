package com.feng.android.base;

import android.app.Application;

import com.feng.android.base.log.timber.TimberUtil;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-27 11:08
 * @tips
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TimberUtil.initLog();
    }
}

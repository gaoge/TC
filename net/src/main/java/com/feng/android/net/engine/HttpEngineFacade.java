package com.feng.android.net.engine;

import android.app.Application;

import com.feng.android.net.engine.retrofit.RetrofitRequest;

import org.xutils.x;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-04 10:06
 * @tips
 */
public class HttpEngineFacade {

    public static void init(Application application){

        PreferenceUtil.getInstance().init(application);
        x.Ext.init(application);

        HttpUtils.initHttpRequest(new RetrofitRequest());
    }
}

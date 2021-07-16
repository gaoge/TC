package com.feng.android.tc;

import android.app.Application;

import com.feng.android.butterknife_annotations.WXPayEntry;
import com.feng.android.common.data.v1.PreferenceUtils;
import com.feng.android.pay.BaseWXPayActivity;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 09:41
 * @tips
 */
@WXPayEntry(packageName = "com.feng.android.tc",entryClass = BaseWXPayActivity.class)
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceUtils.getInstance().init(this);
    }
}

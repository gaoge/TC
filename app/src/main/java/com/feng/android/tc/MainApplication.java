package com.feng.android.tc;

import android.app.Application;
import android.util.Log;

import com.feng.android.base.BaseApplication;
import com.feng.android.base.log.timber.FakeCrashLibrary;
import com.feng.android.base.log.timber.TimberUtil;
import com.feng.android.butterknife_annotations.WXPayEntry;
import com.feng.android.common.data.v1.PreferenceUtils;
import com.feng.android.net.engine.HttpEngineFacade;
import com.feng.android.pay.BaseWXPayActivity;

import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 09:41
 * @tips
 */
@WXPayEntry(packageName = "com.feng.android.tc",entryClass = BaseWXPayActivity.class)
public class MainApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        HttpEngineFacade.init(this);
        initIO();
    }




    private void initIO() {
        PreferenceUtils.getInstance().init(this);
    }
}

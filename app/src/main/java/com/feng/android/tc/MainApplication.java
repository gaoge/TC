package com.feng.android.tc;

import com.feng.android.base.BaseApplication;
import com.feng.android.lib_framework.data.v1.PreferenceUtils;
import com.feng.android.lib_framework.net.engine.HttpEngineFacade;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 09:41
 * @tips
 */
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

package com.feng.android.base;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;
import com.drouter.api.core.DRouter;
import com.feng.android.base.exception.ExceptionCrashHandler;
import com.feng.android.base.log.timber.TimberUtil;
import com.feng.android.base.util.AppUtil;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-27 11:08
 * @tips
 */
public class BaseApplication extends Application {
    public static PatchManager mPatchManager;
    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
        initDRouter();
        initCrashhanlder();
        initAndFix();
    }

    private void initCrashhanlder() {
        ExceptionCrashHandler.getsInstance().init(this);
    }

    private void initTimber() {
        TimberUtil.initLog();
    }

    private void initDRouter() {
        DRouter.openDebug();
        DRouter.getInstance().init(this);
    }

    private void initAndFix() {
        mPatchManager = new PatchManager(this);
        //初始化版本，获取当前应用的版本
        mPatchManager.init(AppUtil.getAppVersionName(this));

        mPatchManager.loadPatch();
    }

    public PatchManager getPatchManager() {
        return mPatchManager;
    }
}

package com.feng.android.tc;

import android.app.Application;
import android.util.Log;

import com.feng.android.base.log.timber.FakeCrashLibrary;
import com.feng.android.butterknife_annotations.WXPayEntry;
import com.feng.android.common.data.v1.PreferenceUtils;
import com.feng.android.pay.BaseWXPayActivity;

import timber.log.Timber;

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

        initIO();
        initLog();

    }

    private void initLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    /** A tree which logs important information for crash reporting. */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag,String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }

    private void initIO() {
        PreferenceUtils.getInstance().init(this);
    }
}

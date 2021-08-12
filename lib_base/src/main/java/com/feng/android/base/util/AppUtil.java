package com.feng.android.base.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-12 15:50
 * @tips
 */
public class AppUtil {
    /**
     * 获取 app 版本号
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context){
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}

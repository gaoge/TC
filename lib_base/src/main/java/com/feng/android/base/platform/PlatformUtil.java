package com.feng.android.base.platform;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import java.io.File;

import androidx.core.content.FileProvider;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-29 17:15
 * @tips
 */
public class PlatformUtil {

    //安装apk文件
    public static void installFile(Context context, File apkFile) {

        Timber.e("installFile");
        Intent intent = new Intent(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, "com.feng.android.third_framework.fileProvider", apkFile);   //authorities就是provider里声明的authorities
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}

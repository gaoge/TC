package com.feng.android.third_framework.okhttp.download.v2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.feng.android.base.encrypt.EncryptUtils;

import java.io.File;

import androidx.core.content.FileProvider;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-29 15:01
 * @tips
 */
final class FileManager {
    private File mRootDir;
    private Context mContext;
    private static final FileManager sManager = new FileManager();

    public static FileManager manager() {
        return sManager;
    }

    public void init(Context context){
        this.mContext = context.getApplicationContext();
    }

    public void rootDir(File file){
        if(!file.exists()){
            file.mkdirs();
        }

        if(file.exists() && file.isDirectory()){
            mRootDir = file;
        }
    }
    /**
     * 通过网络的路径获取一个本地文件路径
     * @param url
     * @return
     */
    public File getFile(String url) {
        String fileName = EncryptUtils.md5(url);
        if(mRootDir == null){
            mRootDir = mContext.getCacheDir();
        }
        File file = new File(mRootDir,fileName);
        return file;
    }

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

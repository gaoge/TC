package com.feng.android.base.exception;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import com.feng.android.base.util.DateUtil;
import com.feng.android.base.util.DeviceUtil;
import com.feng.android.base.util.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-12 13:19
 * @tips
 */
public class ExceptionCrashHandler implements Thread.UncaughtExceptionHandler{
    
    private static ExceptionCrashHandler sInstance;
    private Context mContext;
    //留下原来的，便于开发的时候调试
    private Thread.UncaughtExceptionHandler mDefaultHanlder;
    
    private ExceptionCrashHandler(){
        
    }

    public static ExceptionCrashHandler getsInstance() {
        if(null == sInstance){
            synchronized (ExceptionCrashHandler.class){
                if(null == sInstance){
                    sInstance = new ExceptionCrashHandler();
                }
            }
        }
        return sInstance;
    }
    
    public void init(Context context){
        Thread.currentThread().setUncaughtExceptionHandler(this);
        mDefaultHanlder = Thread.getDefaultUncaughtExceptionHandler();
        this.mContext = context;
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable ex) {
        Timber.e("捕获异常");
        String crashFileName = saveInfoToSD(ex);
        
        cacheCrashFile(crashFileName);
        //系统默认处理
        mDefaultHanlder.uncaughtException(t,ex);

    }

    /**
     * 保存获取的 软件信息，设备信息和出错信息保存在SDcard中
     *
     * @param ex
     * @return
     */
    private String saveInfoToSD(Throwable ex) {
        String fileName = null;
        StringBuffer sb = new StringBuffer();

        for (Map.Entry<String, String> entry : DeviceUtil.obtainSimpleInfo(mContext)
                .entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append(" = ").append(value).append("\n");
        }

        sb.append(obtainExceptionInfo(ex));

        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File dir = new File(mContext.getFilesDir() + File.separator + "crash"
                    + File.separator);

            // 先删除之前的异常信息
            if (dir.exists()) {
                FileUtil.deleteDir(dir);
            }

            // 再从新创建文件夹
            if (!dir.exists()) {
                dir.mkdir();
            }
            try {
                fileName = dir.toString()
                        + File.separator
                        + DateUtil.getAssignTime("yyyy_MM_dd_HH_mm") + ".txt";
                FileOutputStream fos = new FileOutputStream(fileName);
                fos.write(sb.toString().getBytes());
                fos.flush();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    private String obtainExceptionInfo(Throwable ex) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    /**
     * 缓存崩溃日志文件
     *
     * @param fileName
     */
    private void cacheCrashFile(String fileName) {
        SharedPreferences sp = mContext.getSharedPreferences("crash", Context.MODE_PRIVATE);
        sp.edit().putString("CRASH_FILE_NAME", fileName).commit();
    }


    /**
     * 获取崩溃文件名称
     *
     * @return
     */
    public File getCrashFile() {
        String crashFileName = mContext.getSharedPreferences("crash",
                Context.MODE_PRIVATE).getString("CRASH_FILE_NAME", "");
        return new File(crashFileName);
    }

}

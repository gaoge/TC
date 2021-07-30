package com.feng.android.third_framework.okhttp.download.v2;

import android.content.Context;

import com.feng.android.third_framework.okhttp.download.OKDownloadActivity;

import java.io.File;
import java.io.IOException;

import timber.log.Timber;


/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-30 10:52
 * @tips
 */
public class DownloadFacade {
    private static final DownloadFacade sFacade = new DownloadFacade();

    private DownloadFacade(){

    }

    public static DownloadFacade getFacade() {
        return sFacade;
    }

    public void init(Context context){
        FileManager.manager().init(context);
        DaoManagerHelper.getManager().init(context);
    }

    public void startDownload(Context context,String url,DownloadCallback callback){

        DownloadDispatcher.getsDispather().startDownload(url, callback);
    }
}

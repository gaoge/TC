package com.feng.android.third_framework.okhttp.download;

import android.os.Bundle;
import android.view.View;

import com.feng.android.base.BaseActivity;
import com.feng.android.base.platform.PlatformUtil;
import com.feng.android.third_framework.R;
import com.feng.android.third_framework.okhttp.download.v1.DownloadManager;
import com.feng.android.third_framework.okhttp.download.v2.DownloadCallback;
import com.feng.android.third_framework.okhttp.download.v2.DownloadDispatcher;
import com.feng.android.third_framework.okhttp.download.v2.DownloadFacade;
import com.feng.android.third_framework.okhttp.download.v2.DownloadProgressListener;


import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;

import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-29 11:43
 * @tips
 */
public class OKDownloadActivity extends BaseActivity {

    String url = "https://app.fengjr.com/update/4.3.01_202105251746/mobile-website-v4.3.01-release.apk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DownloadFacade.getFacade().init(this);
    }


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
        findViewById(R.id.txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                downloadV1();
                downloadV2();

            }
        });
    }

    //单线程下载
    private void downloadV1() {
        DownloadManager.getsManager().download(OKDownloadActivity.this, url, new DownloadProgressListener() {
            @Override
            public void onFailure(IOException e) {

            }

            @Override
            public void onSuccess(File file) {
                PlatformUtil.installFile(OKDownloadActivity.this,file);
            }

            @Override
            public void progress(long current, long total) {
                String progress = new DecimalFormat("0.00").format(100 * current/Long.valueOf(total).floatValue());
                Timber.e("total: " + total + ",progress: " + progress  + "%" );

            }
        });
    }

    private void downloadV2() {
        long startTime = System.currentTimeMillis();

        //有3个类需要用户去关注，后面我们有可能自己会去更新代码，用户就需要换调用方式
        //所以这里建立一个门面模式
        DownloadFacade.getFacade().startDownload(this,url,new DownloadCallback() {
            @Override
            public void onFailure(IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onSuccess(File file) {
                long endTime = System.currentTimeMillis();
                Timber.e("download take time: " + (endTime - startTime) / 1000 + "s");
                PlatformUtil.installFile(OKDownloadActivity.this,file);
            }
        } );
    }

    @Override
    protected void initTitle() {
        setTitle("Download");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}

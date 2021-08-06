package com.feng.android.third_framework.okhttp.upload;

import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;

import com.feng.android.base.BaseActivity;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-28 15:48
 * @tips
 */
public class UploadProgressActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void setContentView() {
        
    }

    @Override
    protected void initTitle() {
        setTitle("Upload");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        uploadFile();
    }

    private void uploadFile() {

        String url = "https://api.saiwuquan.com/api/upload";

        File file = new File(Environment.getExternalStorageDirectory(), "football.jpeg");

        OkHttpClient httpClient = new OkHttpClient();

        // 构建请求 Body , 这个我们之前自己动手写过
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        builder.addFormDataPart("platform", "android");
        builder.addFormDataPart("file", file.getName(),
                RequestBody.create(MediaType.parse(guessMimeType(file.getAbsolutePath())), file));

        MultipartBodyProxy multipartBodyProxy = new MultipartBodyProxy(builder.build(), new UploadProgressListener() {
            @Override
            public void onProgress(long total, long current) {

            }
        });

        // 构建一个请求
        final Request request = new Request.Builder()
                .url(url)
                .post(multipartBodyProxy).build();
        // new RealCall 发起请求
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Timber.e(response.body().string());
            }
        });
    }

    private String guessMimeType(String filePath) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String mimType = fileNameMap.getContentTypeFor(filePath);
        if(TextUtils.isEmpty(mimType)){
            return "application/octet-stream";
        }
        return mimType ;
    }
}

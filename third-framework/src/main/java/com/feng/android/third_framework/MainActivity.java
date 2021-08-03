package com.feng.android.third_framework;

import android.os.Bundle;

import com.feng.android.base.BaseActivity;
import com.feng.android.third_framework.okhttp.Call;
import com.feng.android.third_framework.okhttp.Callback;
import com.feng.android.third_framework.okhttp.OkHttpClient;
import com.feng.android.third_framework.okhttp.Request;
import com.feng.android.third_framework.okhttp.RequestBody;
import com.feng.android.third_framework.okhttp.Response;
import com.feng.android.third_framework.together.v1.TogetherV1Activity;
import com.feng.android.third_framework.together.v2.TogetherV2Activity;

import java.io.File;
import java.io.IOException;

import timber.log.Timber;

//import java.io.IOException;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-27 10:48
 * @tips
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(TogetherV2Activity.class);
        finish();
    }

    @Override
    protected void setContentView() {

    }

    @Override
    protected void initTitle() {
        setTitle("MainActivity");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//        thirdUsage();

//        ggOkhttp();

        //设计的类比较多，写出来代码的思想(UML),体现的调用形式搭起来，再把里面的细节填好
    }


    File file = new File("");
    private void ggOkhttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        RequestBody requestBody = new RequestBody()
                .type(RequestBody.FROM)
                .addParam("file",RequestBody.create(file))
                .addParam("pageNo",1 +"")
                .addParam("pageSize",1+"")
                .addParam("platform","android");
        Request request = new Request.Builder()
                .url("https://api.saiwuquan.com/api/appv2/sceneModel")
                .post(requestBody)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Timber.e(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Timber.e(response.string());
            }
        });
    }

//    private void thirdUsage() {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        //1. 构建一个请求,url,端口，请求头的一些参数，表单提交(contentType,contentLength)
//        Request request = new Request.Builder()
//                //307 Location : https://www.baidu.com
//                .url("https://www.baidu.com")
//                .build();
//        //2. 把Request转成一个Call
//        Call call = okHttpClient.newCall(request);
//        //3. enqueue : 队列处理
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String result = response.body().string();
////                Toast.makeText(OkHttpMainActivity.this,result,Toast.LENGTH_SHORT).show();
//                Timber.e("result: " + result);
//            }
//        });
//    }
}

package com.feng.android.third_framework.rxjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.feng.android.base.BaseActivity;
import com.feng.android.base.image.ImageUtil;
import com.feng.android.third_framework.R;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import timber.log.Timber;


/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-30 11:31
 * @tips
 */
public class MyRxjavaActivity extends BaseActivity {
    String imageUrl = "https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/baike/s%3D600%3Bq%3D50/sign=0cce1c7f70cf3bc7ec00ceece13bcb9c/91529822720e0cf3bc0dae890a46f21fbf09aace.jpg";

    ImageView imageView;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        imageView = findViewById(R.id.img);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1. 有没有权限
        //2. 没有申请
        //3. 申请之后用户是否给了权限
        //4. 根据是否有权限，进行相应的操作

        //给网络上的一张图片加水印，显示到ImageView上
        //1. 开启线程下载图片
        //2. 加水印
        //3. 切换到主线程，显示图片
//        handleImageV1();
//        handleJustSubscribe();
        handleConsumer();
    }

    private void handleConsumer() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Observable.just(imageUrl)
                        .map(new Function<String, Bitmap>() {
                            @Override
                            public Bitmap apply(String s) throws Exception {
                                URL url = new URL(imageUrl);
                                URLConnection urlConnection = url.openConnection();
                                InputStream inputStream = urlConnection.getInputStream();
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                return bitmap;
                            }
                        })
                        .map(new Function<Bitmap, Bitmap>() {
                            @Override
                            public Bitmap apply(Bitmap bitmap) throws Exception {
                                return ImageUtil.createWatermark(bitmap,"MyRxjava");
                            }
                        })
                        .subscribe(new Consumer<Bitmap>() {
                            @Override
                            public void onNext(Bitmap item) {
                                Timber.e("onNext,item: " + item);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        imageView.setImageBitmap(item);
                                    }
                                });
                            }
                        });
            }
        }).start();


    }

    private void handleJustSubscribe() {
        //好处 ？可读性比较高，一条链子下来的(可读性高)，扩展性，维护性等
        //学些成本要高，思想难以转换(事件流)
        Observable.just(imageUrl)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe() {
                        Timber.e("onSubscribe");

                    }

                    @Override
                    public void onNext(String s) {
                        Timber.e("onNext");
                        Integer.parseInt("ss");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("onError");
                    }

                    @Override
                    public void onComplete() {
                        Timber.e("onComplete");
                    }
                });

        //怎么办到的，这个框架怎么写？可能会涉及哪些内容
    }


}

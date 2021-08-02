package com.feng.android.third_framework.rx.rxjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.feng.android.base.BaseActivity;
import com.feng.android.base.image.ImageUtil;
import com.feng.android.third_framework.R;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-30 11:31
 * @tips
 */
public class RxjavaActivity extends BaseActivity {
    String imageUrl = "https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/baike/s%3D600%3Bq%3D50/sign=0cce1c7f70cf3bc7ec00ceece13bcb9c/91529822720e0cf3bc0dae890a46f21fbf09aace.jpg";

    ImageView imageView;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bitmap bitmap = (Bitmap) msg.obj;
            imageView.setImageBitmap(bitmap);
        }
    };
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
        handleImageV2();
    }

    private void handleImageV2() {
        //好处 ？可读性比较高，一条链子下来的(可读性高)，扩展性，维护性等
        //学些成本要高，思想难以转换(事件流)
        Observable.just(imageUrl)
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Throwable {
                        URL url = new URL(imageUrl);
                        URLConnection urlConnection = url.openConnection();
                        InputStream inputStream = urlConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        return bitmap;
                    }
                })
                .map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(Bitmap bitmap) throws Throwable {
                       return ImageUtil.createWatermark(bitmap,"Rxjava3");
                    }
                })
                .subscribeOn(Schedulers.io()) //之前的代码执行在子线程中
                .observeOn(AndroidSchedulers.mainThread()) //之后的代码执行在主线程中
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Throwable {
                        imageView.setImageBitmap(bitmap);
                    }
                });

        //怎么办到的，这个框架怎么写？可能会涉及哪些内容
    }

    private void handleImageV1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //1. 下载图片
                    URL url = new URL(imageUrl);
                    URLConnection urlConnection = url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    //2. 添加水印
                    bitmap = ImageUtil.createWatermark(bitmap,"Gaoge");
                    //3. 显示到ImageView,需要切换线程
                    Message msg = Message.obtain();
                    msg.obj = bitmap;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

package com.feng.android.third_framework.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.feng.android.base.BaseActivity;
import com.feng.android.butterknife.Butterknife;
import com.feng.android.butterknife_annotations.BindView;
import com.feng.android.third_framework.R;
import com.feng.android.third_framework.eventbus.Subscribe;
import com.feng.android.third_framework.eventbus.TestActivity;
import com.feng.android.third_framework.eventbus.ThreadMode;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends BaseActivity {

    @BindView(R.id.txt)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GEventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN,priority = 50,sticky = true)
    public void test1(String msg){
        tv.setText(msg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,priority = 100,sticky = true)
    public void test2(String msg){
        tv.setText(msg);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        Butterknife.bind(this);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TestActivity.class);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GEventBus.getDefault().unregister(this);
    }
}
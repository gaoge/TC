package com.feng.android.third_framework.eventbus;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.feng.android.base.BaseActivity;
import com.feng.android.butterknife.Butterknife;
import com.feng.android.butterknife_annotations.BindView;
import com.feng.android.third_framework.R;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-23 17:45
 * @tips
 */
public class TestActivity extends BaseActivity {
    @BindView(R.id.txt)
    TextView tv;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
        GEventBus.getDefault().register(this);
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
                GEventBus.getDefault().post("text");
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

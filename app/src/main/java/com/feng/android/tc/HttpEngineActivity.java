package com.feng.android.tc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.feng.android.base.BaseActivity;
import com.feng.android.net.engine.HttpCallBack;
import com.feng.android.net.engine.HttpUtils;
import com.feng.android.tc.entity.UpdateEntity;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import timber.log.Timber;

public class HttpEngineActivity extends BaseActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtils.with(HttpEngineActivity.this).url("https://update.fengjr.com/update/api/v1/app/dev/4.3.00/29/huawei").cache(true)
//                        .post()
                        .request(new HttpCallBack<UpdateEntity>() {
                            @Override
                            public void onFailure(Exception e) {
                                Timber.e(e.getMessage());
                            }

                            @Override
                            public void onSuccess(UpdateEntity data) {
                                Timber.e(data.toString());
                            }
                        });
            }
        });

    }

    @Override
    protected void setContentView() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
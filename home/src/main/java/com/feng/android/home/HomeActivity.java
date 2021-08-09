package com.feng.android.home;

import android.os.Bundle;
import android.view.View;

import com.drouter.api.core.DRouter;
import com.feng.android.base.BaseActivity;


/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-09 12:50
 * @tips
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_home);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DRouter.getInstance().action("/insurance/list")
                        .context(HomeActivity.this)
                        .invokeAction();
            }
        });
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

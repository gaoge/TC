package com.feng.android.tc;

import android.os.Bundle;
import android.view.View;

import com.feng.android.tc.desingpattern.singleton.manager.ActivityManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 17:19
 * @tips
 */
public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle("RegisterActivity");
        ActivityManager.getInstance().attach(this);

    }

    public void click(View view){
        //不光要关闭自己，还要关闭LoginActivity 让其返回到主页
        ActivityManager.getInstance().finish(this);
        ActivityManager.getInstance().finish(LoginActivity.class);
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().detach(this);
        super.onDestroy();
    }
}

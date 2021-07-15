package com.feng.android.tc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.feng.android.tc.desingpattern.singleton.manager.ActivityManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 17:19
 * @tips
 */
public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityManager.getInstance().attach(this);

        setTitle("LoginActivity");
    }

    public void click(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().detach(this);
        super.onDestroy();
    }


}

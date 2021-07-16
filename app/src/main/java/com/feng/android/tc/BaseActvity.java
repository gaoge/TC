package com.feng.android.tc;

import android.os.Bundle;

import com.feng.android.tc.desingpattern.singleton.manager.ActivityManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 10:59
 * @tips
 */
public class BaseActvity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManager.getInstance().attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityManager.getInstance().detach(this);
    }
}

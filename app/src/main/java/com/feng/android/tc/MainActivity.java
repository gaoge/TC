package com.feng.android.tc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.feng.android.net.CheckNet;
import com.feng.android.net.NetUtil;

public class MainActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.btn);
        NetUtil.networkAvailable(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test1();
            }
        });
    }

    @CheckNet
    private void test1() {
        Toast.makeText(this,"Open Detail Activity",Toast.LENGTH_SHORT).show();
    }
}
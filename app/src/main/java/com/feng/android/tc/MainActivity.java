package com.feng.android.tc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.feng.android.butterknife.Butterknife;
import com.feng.android.butterknife.Unbinder;
import com.feng.android.butterknife_annotations.BindView;
import com.feng.android.common.ui.navigationBar.NavigationBar;
import com.feng.android.net.CheckNet;
import com.feng.android.net.NetUtil;
import com.feng.android.tc.desingpattern.singleton.manager.ActivityManager;

public class MainActivity extends BaseActvity {

    @BindView(R.id.btn)
    Button btn;

    @BindView(R.id.tv)
    TextView tv;

    private Unbinder myUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myUnbinder = Butterknife.bind(this);
//        btn = (Button)findViewById(R.id.btn);
        NetUtil.networkAvailable(this);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                test1();
//            }
//        });
        setTitle("MainActivity");

    }

    @CheckNet
    private void test1() {
        Toast.makeText(this,"Open Detail Activity",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        myUnbinder.unbind();
        super.onDestroy();
    }

    public void click(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

}
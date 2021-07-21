package com.feng.android.insurance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.feng.android.base.BaseActivity;
import com.feng.android.butterknife.Butterknife;
import com.feng.android.butterknife_annotations.BindView;
import com.feng.android.insurance.model.Member;

public class InsuranceActivity extends BaseActivity {

    @BindView(R.id.etName)
    EditText etName;

    @BindView(R.id.etAge)
    EditText etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_insurance);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        myUnbinder = Butterknife.bind(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    public void add(View view) {
        //添加购买保险的人员

        //插入数据库(这步肯定是不能少的)
        DatabaseManager.getInstance().insert(new Member(etName.getText().toString(),etAge.getText().toString()));

        //怎么去通知我们的主Activity去更新?
        //1.可以当关闭的时候，可以去setResult，会调用MainActivity的onActivityResult

        //2.可以利用EventBus去发送更新

        //3.还有就是当页面关闭的时候去查询数据库

        //4.利用观察者模式去更新我们的列表
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        myUnbinder.unbind();
    }

    public void finish(View view) {
        finish();
    }



}
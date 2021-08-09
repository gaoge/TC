package com.feng.android.insurance;



import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.drouter.api.action.IRouterAction;
import com.drouter.api.result.RouterResult;
import com.drouter.base.ThreadMode;
import com.drouter.base.annotation.Action;
import com.feng.android.base.BaseActivity;
import com.feng.android.butterknife.Butterknife;


import com.feng.android.insurance.model.Member;

import java.util.Map;

@Action(path="/insurance/list",threadMode = ThreadMode.MAIN,extraProcess = true)
public class InsuranceActivity extends BaseActivity implements IRouterAction {


    EditText etName;


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
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
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


    @Override
    public RouterResult invokeAction(Context context, Map<String, Object> requestData) {
        startActivity(InsuranceActivity.class);
        return null;
    }
}
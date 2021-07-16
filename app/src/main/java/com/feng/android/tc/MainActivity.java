package com.feng.android.tc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.feng.android.butterknife.Butterknife;
import com.feng.android.butterknife.Unbinder;
import com.feng.android.butterknife_annotations.BindView;
import com.feng.android.common.data.v2.IOHandler;
//import com.feng.android.common.data.v2.IOHandlerFactory;
import com.feng.android.common.data.v3.IOFactory;
import com.feng.android.common.data.v3.MemoryIOFactory;
//import com.feng.android.common.data.v4.IOHandlerFactory;
import com.feng.android.common.data.v5.IOHandlerFactory;
import com.feng.android.net.CheckNet;
import com.feng.android.net.NetUtil;

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
        setTitle("MainActivity");

        //问题的分析 ？ 我们一般都会有一个清理缓存的功能，是不是以后需要去清理，
        //清理的时候某些特定内容我们可能不想清理，可能以后为了保证性能，我们采取磁盘的存储内存的存储或者采取数据库存储等
        //v1
//        PreferenceUtils.getInstance()
//                .saveString("userName","darren")
//                .saveString("userAge","28")
//                .commit();

        //v2
//        IOHandler ioHandler = IOHandlerFactory.createIOHandler(IOHandlerFactory.IOType.MEMORY);

        //v3
//        IOFactory ioFactory = new MemoryIOFactory();
//        IOHandler ioHandler = ioFactory.createIOHandler();

        //v4
//        IOHandler ioHandler = IOHandlerFactory.getDefaultIOHandler();

        //v5
        IOHandler ioHandler = IOHandlerFactory.getInstance().getDefaultIOHandler();


        ioHandler.save("userName","darren");
        ioHandler.save("userAge","28");


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
//        Intent intent = new Intent(this,LoginActivity.class);
//        startActivity(intent);

//        String userName = PreferenceUtils.getInstance().getString("userName");
//        String userAge = PreferenceUtils.getInstance().getString("userAge");

//        IOHandler ioHandler = IOHandlerFactory.createIOHandler(IOHandlerFactory.IOType.MEMORY);

//        IOFactory ioFactory = new MemoryIOFactory();
//        IOHandler ioHandler = ioFactory.createIOHandler();

        IOHandler ioHandler = IOHandlerFactory.getInstance().getDefaultIOHandler();;

        String userName = ioHandler.getString("userName");
        String userAge = ioHandler.getString("userAge");

        tv.setText("userName = " + userName + ",userAge = " + userAge) ;
    }

}
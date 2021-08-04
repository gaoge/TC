package com.feng.android.tc;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.feng.android.base.BaseActivity;
import com.feng.android.butterknife.Butterknife;
import com.feng.android.butterknife_annotations.BindView;
import com.feng.android.common.data.v2.IOHandler;
//import com.feng.android.common.data.v2.IOHandlerFactory;
//import com.feng.android.common.data.v4.IOHandlerFactory;
import com.feng.android.common.data.v5.IOHandlerFactory;
import com.feng.android.common.ui.bottomBar.iterator.DefaultBottomTabIterator;
import com.feng.android.common.ui.bottomBar.TabBottomNavigation;
import com.feng.android.common.ui.listview.DarrenListView;
import com.feng.android.common.ui.listview.ListAdapter;
import com.feng.android.common.ui.recyclerview.WrapRecyclerView;
import com.feng.android.net.CheckNet;
import com.feng.android.net.NetUtil;
import com.feng.android.common.ui.bottomBar.DefaultBottomTabItem;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.recycler_view)
    WrapRecyclerView mRecyclerView;
    @BindView(R.id.darren_lv)
    DarrenListView mListView;
    @BindView(R.id.tab_bottom)
    TabBottomNavigation mTabBottomNavigation;

    private List<String> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tcIOHandler();
//        tcWrapRecyclerView();
        tcAnimator();
        tcLog();
//        tcListView();
        tcBottomBar();

        startActivity(HttpEngineActivity.class);
        finish();
    }

    private void tcBottomBar() {
        DefaultBottomTabIterator<DefaultBottomTabItem> listIterator = new DefaultBottomTabIterator<>();

        listIterator.add(new DefaultBottomTabItem.Builder(this)
                .resIconId(R.drawable.main_bottom_tab_item)
                .text("首页")
                .textColor(R.color.main_bottom_tab_item_color).create());
        listIterator.add(new DefaultBottomTabItem.Builder(this)
                .resIconId(R.drawable.main_bottom_tab_item)
                .text("理财")
                .textColor(R.color.main_bottom_tab_item_color).create());
        listIterator.add(new DefaultBottomTabItem.Builder(this)
                .resIconId(R.drawable.main_bottom_tab_item)
                .text("我的")
                .textColor(R.color.main_bottom_tab_item_color).create());

        //还可以写的更灵活些
        //有可能我们不传List
        mTabBottomNavigation.addTabItems(listIterator, i -> tv.setText("" + i));
    }

    private void tcListView() {
        ListView lv;
        //假设后台返回的数据是一个集合，我们要显示的是列表，是View,
        for(int i=0;i<20;i++){
            mItems.add(i+ "");
        }

        //v1 一般写法,for循环不断的添加View
//        for(String item:mItems){
//            TextView itemView = (TextView) LayoutInflater.from(this).inflate(R.layout.item_recyclerview, null);
//            itemView.setText(item);
//            mListView.addView(itemView);
//        }

        //v2 adapters设计模式
        mListView.setAdapter(new ListAdapter(mItems,this));
    }

    private void tcLog() {
        //但是打印low的不行，所以我们一般要自己扩展，配合我们的Logger一起
        Timber.tag("LifeCycles");
        Timber.d("MainActivity Created");
    }

    private void tcAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv,"scaleX",1f);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(4000).start();

        //如果要让我们自己去实现动画？怎么实现？猜测
        //开一个线程不断的调用setScaleX()这个方法，传的值是后面0f,1f,2f参数
        //怎么才能调用到imageView的setScaleX方法，通过反射调用
    }

    private void tcIOHandler() {
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

    private void tcWrapRecyclerView() {

        mRecyclerView.setVisibility(View.GONE);


        for(int i=0;i<3;i++){
            mItems.add(i + "-");
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //采用装饰设计模式，让其支持添加头部和底部
        ReyclerAdapter realAdapter = new ReyclerAdapter();
//        WrapRecyclerAdapter wrapRecyclerAdapter = new WrapRecyclerAdapter(realAdapter);

        //添加头部
        View headerView = LayoutInflater.from(this).inflate(R.layout.recyler_header_view, mRecyclerView,false);
        View footerView = LayoutInflater.from(this).inflate(R.layout.recyler_footer_view, mRecyclerView,false);
        //v1
//        mRecyclerView.setAdapter(wrapRecyclerAdapter);
//        wrapRecyclerAdapter.addHeaderView(headerView);

        //WrapRecyclerAdapter如果这样写，面向对象的六大基本原则在哪里？最少知识原则又在哪里呢？必须要像ListView让其支持,所以v2来新建一个WrapRecyclerView,支持直接给WrapRecyclerView来添加headerView
        //v2
        mRecyclerView.setAdapter(realAdapter);
        mRecyclerView.addHeaderView(headerView);
        mRecyclerView.addFooterView(footerView);

        //不要把代码过度封装，在我看来，业务逻辑能分开就分开；在底层和中间层（不含业务逻辑的）能封装就封装，不用过度纠结封装
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initTitle() {
        setTitle("MainActivity");
    }

    @Override
    protected void initView() {
        myUnbinder = Butterknife.bind(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        NetUtil.networkAvailable(this);
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

    private class ReyclerAdapter extends RecyclerView.Adapter<ReyclerAdapter.ViewHolder>{


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_recyclerview,parent,false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText("position = " + mItems.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItems.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            public TextView text;
            public View itemView;
            public ViewHolder(View itemView) {
                super(itemView);
                this.itemView = itemView;
                text = (TextView)itemView.findViewById(R.id.text);
            }
        }
    }


}
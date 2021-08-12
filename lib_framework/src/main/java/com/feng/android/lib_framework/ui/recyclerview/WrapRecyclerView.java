package com.feng.android.lib_framework.ui.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-19 10:18
 * @tips
 */
public class WrapRecyclerView extends RecyclerView {
    private WrapRecyclerAdapter mAdapter;

    public WrapRecyclerView( Context context) {
        super(context);
    }

    public WrapRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRecyclerView( Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        mAdapter = new WrapRecyclerAdapter(adapter);
        super.setAdapter(mAdapter);
    }

    public void addHeaderView(View view){
        //必须要设置Adapter之后才能添加头部和底部
        if(mAdapter != null){
            mAdapter.addHeaderView(view);
        }
    }
    public void addFooterView(View view){
        //必须要设置Adapter之后才能添加头部和底部
        if(mAdapter != null){
            mAdapter.addFooterView(view);
        }

    }
    public void removeHeaderView(View view){
        //必须要设置Adapter之后才能添加头部和底部
        if(mAdapter != null){
            mAdapter.removeHeaderView(view);
        }

    }
    public void removeFooterView(View view){
        //必须要设置Adapter之后才能添加头部和底部
        if(mAdapter != null){
            mAdapter.removeFooterView(view);
        }
    }
}

package com.feng.android.common.ui.recyclerview;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 18:09
 * @tips
 * 装饰设计模式的RecyclerView.Adpater,我们对其进行功能扩展，使它支持头部和底部的添加
 */
public class WrapRecyclerAdapter extends RecyclerView.Adapter {

    //原来的RecyclerView.Adpater,并不支持头部和底部的添加
    private RecyclerView.Adapter mRealAdapter;
    ArrayList<View> mHeaderViews;
    ArrayList<View> mFooterViews;

    public WrapRecyclerAdapter(RecyclerView.Adapter realAdapter) {
        this.mRealAdapter = realAdapter;
        mHeaderViews = new ArrayList<>();
        mFooterViews = new ArrayList<>();

        mRealAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        //直接把position作为viewType
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        //问题 如果想知道是哪个部分，必须要知道position,但是目前只有viewType
        //1.头部 返回头部的ViewHolder
        int numHeaders = getHeadersCount();
        if(position < numHeaders){
            return createHeaderFooterViewHolder(mHeaderViews.get(position));
        }
        //2.mRealAdapter 返回mRealAdapter的ViewHolder
        final int adjPostion = position - numHeaders;
        int adapterCount = 0;
        if(mRealAdapter != null){
            adapterCount = mRealAdapter.getItemCount();
            if(adjPostion < adapterCount){
                //直接传postion,会不兼容 万能适配多布局条目
                return mRealAdapter.onCreateViewHolder(parent,mRealAdapter.getItemViewType(adjPostion));
            }
        }
        //3.底部 返回底部的ViewHolder
        return createHeaderFooterViewHolder(mFooterViews.get(adjPostion-adapterCount));


    }

    private RecyclerView.ViewHolder createHeaderFooterViewHolder(View view) {
        return new RecyclerView.ViewHolder(view){};
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        //头部和底部都是不需要做处理的，只有mRealAdapter需要处理
        int numHeaders = getHeadersCount();
        if(position < numHeaders){
            return ;
        }
        final int adjPostion = position - numHeaders;
        int adapterCount = 0;
        if(mRealAdapter != null){
            adapterCount = mRealAdapter.getItemCount();
            if(adjPostion < adapterCount){
                //直接传postion,会不兼容 万能适配多布局条目
                mRealAdapter.onBindViewHolder(holder,adjPostion);
            }
        }
    }

    @Override
    public int getItemCount() {//总共返回多少条 = 头部 + 底部 + 真实的Adapter条数
        int count = mHeaderViews.size() + mFooterViews.size() + mRealAdapter.getItemCount();
        Log.d("wrapR","count: " + count);
        return count;
    }

    public void addHeaderView(View view){
        if(!mHeaderViews.contains(view)){
            mHeaderViews.add(view);
            notifyDataSetChanged();
        }
    }
    public void addFooterView(View view){
        if(!mFooterViews.contains(view)){
            mFooterViews.add(view);
            notifyDataSetChanged();
        }

    }
    public void removeHeaderView(View view){
        if(mHeaderViews.contains(view)){
            mHeaderViews.remove(view);
            notifyDataSetChanged();
        }

    }
    public void removeFooterView(View view){
        if(mFooterViews.contains(view)){
            mFooterViews.remove(view);
            notifyDataSetChanged();
        }
    }

    public int getHeadersCount(){
        return mHeaderViews.size();
    }

    public int getFootersCount(){
        return mFooterViews.size();
    }
}

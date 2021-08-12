package com.feng.android.lib_framework.ui.bottomBar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 17:48
 * @tips
 * BottomTabItem 基类
 * 
 */
public abstract class AbsBottomTabItem {
    //布局 id Context,最好采用Builder设计模式
    //底部的子item
    private View mTabItemView;
    private Context mContext;
    private int mLayoutId;

    public AbsBottomTabItem(int layoutId, Context context) {
        this.mLayoutId = layoutId;
        this.mContext = context;
    }

    /**
     * 获取底部条目的显示
     * @return
     */
    public View getTabView(){
        if(null == mTabItemView){
            mTabItemView = LayoutInflater.from(mContext).inflate(mLayoutId,null);
            initLayout();
        }
        return mTabItemView;
    }

    /**
     * 初始化显示
     */
    protected abstract void initLayout();

    protected <T> T findViewById(int id){
        return (T) mTabItemView.findViewById(id);
    }
    
    abstract protected void setSelected(boolean selected);
}

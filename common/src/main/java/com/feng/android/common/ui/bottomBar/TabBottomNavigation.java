package com.feng.android.common.ui.bottomBar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.feng.android.common.ui.bottomBar.iterator.IBottomTabIterator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 17:47
 * @tips
 */
public class TabBottomNavigation extends LinearLayout {
    private List<AbsBottomTabItem> mTabItems = new ArrayList<>();
    public TabBottomNavigation(Context context) {
        this(context,null);
    }
    private int mCurrentIndex = -1;

    private BottomBarItemClickListener mBottomBarItemClickListener;

    public TabBottomNavigation(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TabBottomNavigation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
    }

    public void addTabItems(IBottomTabIterator tabIterator,BottomBarItemClickListener bottomBarItemClickListener){
        this.mBottomBarItemClickListener = bottomBarItemClickListener;
        mTabItems.clear();

        //当前的位置
        int index=0;
        while (tabIterator.hasNext()){
            AbsBottomTabItem tabItem = tabIterator.next();

            View tabView = tabItem.getTabView();
            addView(tabView);

            LinearLayout.LayoutParams params = (LayoutParams)tabView.getLayoutParams();
            params.weight = 1;
            params.gravity = Gravity.CENTER;
            tabView.setLayoutParams(params);

            //给条目设置点击事件，等等
            setItemClickListener(tabView,index++);

           mTabItems.add(tabItem);
        }

        //第一个位置要设置为选中
        mTabItems.get(0).setSelected(true);
        mCurrentIndex = 0;


    }

    private void setItemClickListener(View tabView, int position) {
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex != position){
                    //原来的设置为非选中
                    mTabItems.get(mCurrentIndex).setSelected(false);

                    //把当前设置为选中
                    mCurrentIndex = position;
                    mTabItems.get(mCurrentIndex).setSelected(true);

                    //把点击的位置用接口回调回去，共外部使用，调整显示
                    mBottomBarItemClickListener.onItemClick(position);
                }
            }
        });
    }

    public interface BottomBarItemClickListener{
        void onItemClick(int i);
    }
}

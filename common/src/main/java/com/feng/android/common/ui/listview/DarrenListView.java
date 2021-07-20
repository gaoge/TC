package com.feng.android.common.ui.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 13:52
 * @tips
 */
public class DarrenListView extends ScrollView {
    private LinearLayout mContainer;
    private AdapterTarget mAdapter;

    public DarrenListView(Context context) {
        this(context,null);
    }

    public DarrenListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DarrenListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContainer = new LinearLayout(context);
        mContainer.setOrientation(LinearLayout.VERTICAL);
        addView(mContainer,0);
    }

    @Override
    public void addView(View child) {
        mContainer.addView(child);
    }

    public void setAdapter(ListAdapter listAdapter) {
        mAdapter = listAdapter;

        int count = mAdapter.getCount();

        for(int i=0;i<count;i++){
            View childView = mAdapter.getView(i,mContainer);
            addView(childView);
        }
    }
}

package com.feng.android.lib_framework.ui.bottomBar.iterator;

import com.feng.android.lib_framework.ui.bottomBar.AbsBottomTabItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-22 14:35
 * @tips
 */
public class DefaultBottomTabIterator<T extends AbsBottomTabItem> implements IBottomTabIterator {
    private List<T> mTabItems;
    private int index;

    public DefaultBottomTabIterator() {
        this.mTabItems = new ArrayList<>();
    }

    public void add(T item){
        mTabItems.add(item);
    }

    @Override
    public AbsBottomTabItem next() {
        return mTabItems.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < mTabItems.size();
    }
}

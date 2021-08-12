package com.feng.android.lib_framework.ui.listview;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 15:05
 * @tips
 */
public interface AdapterTarget {
    int getCount();
    View getView(int position, ViewGroup parent);
}

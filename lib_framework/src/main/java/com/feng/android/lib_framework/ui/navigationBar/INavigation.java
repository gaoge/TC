package com.feng.android.lib_framework.ui.navigationBar;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 10:32
 * @tips
 */
public interface INavigation {
    void createNavigationBar();

    /**
     * 绑定参数
     */
    void attachNavigationParams();

    /**
     * 添加到父布局
     * @param navigationBar
     * @param parent
     */
    void attachParent(View navigationBar, ViewGroup parent);
}

package com.feng.android.common.ui.bottomBar.iterator;

import com.feng.android.common.ui.bottomBar.AbsBottomTabItem;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-22 14:31
 * @tips
 */
public interface IBottomTabIterator {
    AbsBottomTabItem next();
    boolean hasNext();
}

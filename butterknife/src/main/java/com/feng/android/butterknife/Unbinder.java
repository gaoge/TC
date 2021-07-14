package com.feng.android.butterknife;

import androidx.annotation.UiThread;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-14 11:07
 * @tips
 */
public interface Unbinder {

    @UiThread
    void unbind();

    Unbinder EMPTY = new Unbinder() {
        @Override
        public void unbind() {
            unbind();
        }
    };
}

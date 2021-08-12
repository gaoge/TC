package com.feng.android.lib_framework.data.v3;

import com.feng.android.lib_framework.data.v2.IOHandler;
import com.feng.android.lib_framework.data.v2.PreferenceIOHanlder;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 15:48
 * @tips
 */
public class PreferencesIOFactory implements IOFactory {
    @Override
    public IOHandler createIOHandler() {
        return new PreferenceIOHanlder();
    }
}

package com.feng.android.lib_framework.data.v3;

import com.feng.android.lib_framework.data.v2.DiskIOHandler;
import com.feng.android.lib_framework.data.v2.IOHandler;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 15:50
 * @tips
 */
public class DiskIOFactory implements IOFactory {
    @Override
    public IOHandler createIOHandler() {
        return new DiskIOHandler();
    }
}

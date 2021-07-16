package com.feng.android.common.data.v3;

import com.feng.android.common.data.v2.DiskIOHandler;
import com.feng.android.common.data.v2.IOHandler;

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

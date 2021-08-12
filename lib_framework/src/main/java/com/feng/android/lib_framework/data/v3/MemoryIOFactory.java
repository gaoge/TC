package com.feng.android.lib_framework.data.v3;

import com.feng.android.lib_framework.data.v2.IOHandler;
import com.feng.android.lib_framework.data.v2.MemoryIOHandler;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 15:47
 * @tips
 * 运行内存存储的Factory
 */
public class MemoryIOFactory implements IOFactory {
    @Override
    public IOHandler createIOHandler() {
        return new MemoryIOHandler();
    }
}

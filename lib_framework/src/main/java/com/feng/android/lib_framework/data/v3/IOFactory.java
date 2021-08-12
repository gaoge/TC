package com.feng.android.lib_framework.data.v3;

import com.feng.android.lib_framework.data.v2.IOHandler;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 15:45
 * @tips
 * 工厂设计模式-工厂方法模式
 * 问题：随着功能的扩展，我们的IOFactory类会不断的增加，而且逻辑基本一样，在一定程度代码冗余度高
 */
public interface IOFactory {
    IOHandler createIOHandler();
}

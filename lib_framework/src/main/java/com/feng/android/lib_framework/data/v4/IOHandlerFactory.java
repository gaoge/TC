package com.feng.android.lib_framework.data.v4;

import com.feng.android.lib_framework.data.v2.DiskIOHandler;
import com.feng.android.lib_framework.data.v2.IOHandler;
import com.feng.android.lib_framework.data.v2.MemoryIOHandler;
import com.feng.android.lib_framework.data.v2.PreferenceIOHanlder;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 15:56
 * @tips
 * 工厂设计模式-抽象工厂模式
 * 定义跟BitmapFactory比较像，也跟简单工厂模式比较像，通过特定的方法返回单一的对象
 */
public class IOHandlerFactory {
    //如果觉得有必要，那么完全可以写成单例设计模式，参照v5

    public static IOHandler createIOHandler(Class<? extends IOHandler> ioHandlerClass){
        try {
            return ioHandlerClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PreferenceIOHanlder();
    }

    /**
     * 获取运行内存存储
     * @return
     */
    public static IOHandler getMemoryIOHandler(){
        return createIOHandler(MemoryIOHandler.class);
    }

    /**
     * 获取磁盘存储
     * @return
     */
    public static IOHandler getDiskIOHandler(){
        return createIOHandler(DiskIOHandler.class);
    }

    /**
     * 获取SP 存储
     * @return
     */
    public static IOHandler getPreferenceIOHandler(){
        return createIOHandler(PreferenceIOHanlder.class);
    }

    /**
     * 获取默认存储模式
     * 为什么搞个默认的，有时候代码写完了，但是网上有很多高效的，
     * 又或者是本来用了别人的，但是某些人出了更好的，这样方便切换
     * @return
     */
    public static IOHandler getDefaultIOHandler(){
        return getPreferenceIOHandler();
    }
}

package com.feng.android.lib_framework.data.v5;

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

    private volatile static IOHandlerFactory mInstance;
    private IOHandler mMemoryIOHandler,mPreferenceIOHandler,mDisIOHandler;

    private IOHandlerFactory(){

    }

    public static IOHandlerFactory getInstance(){
        if(null == mInstance){
            synchronized (IOHandlerFactory.class){
                if(null == mInstance){
                    mInstance = new IOHandlerFactory();
                }
            }
        }
        return mInstance;
    }

    public IOHandler createIOHandler(Class<? extends IOHandler> ioHandlerClass){
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
    public IOHandler getMemoryIOHandler(){
        if(mMemoryIOHandler == null){
            mMemoryIOHandler = createIOHandler(MemoryIOHandler.class);
        }
        return mMemoryIOHandler;
    }

    /**
     * 获取磁盘存储
     * @return
     */
    public IOHandler getDiskIOHandler(){
        if(mDisIOHandler == null){
            mDisIOHandler =  createIOHandler(DiskIOHandler.class);
        }
        return mDisIOHandler;
    }

    /**
     * 获取SP 存储
     * @return
     */
    public IOHandler getPreferenceIOHandler(){
        if(mPreferenceIOHandler == null){
            mPreferenceIOHandler = createIOHandler(PreferenceIOHanlder.class);
        }
        return mPreferenceIOHandler;
    }

    /**
     * 获取默认存储模式
     * 为什么搞个默认的，有时候代码写完了，但是网上有很多高效的，
     * 又或者是本来用了别人的，但是某些人出了更好的，这样方便切换
     * @return
     */
    public IOHandler getDefaultIOHandler(){
        return getPreferenceIOHandler();
    }
}

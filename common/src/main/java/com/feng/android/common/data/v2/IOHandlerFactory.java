package com.feng.android.common.data.v2;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 15:25
 * @tips
 *
 * 工厂设计模式- 简单工厂模式
 * 问题，比如我新增一个新的方式存储，要怎么改？
 * 需要新增类型，需要添加case，说白了就是要修改原来的很多代码
 */
public class IOHandlerFactory {
    public enum IOType{
        MEMORY,PREFERENCES,DISK
    }


    public static IOHandler createIOHandler(IOType ioType){
        switch (ioType){
            case MEMORY:
                //直接返回一个对象，有时候我们需要创建对象后，要进行一系列的初始化参数
                return new MemoryIOHandler();
            case PREFERENCES:
                return new PreferenceIOHanlder();
            case DISK:
                return new DiskIOHandler();
            default:
                return null;
        }
    }

}

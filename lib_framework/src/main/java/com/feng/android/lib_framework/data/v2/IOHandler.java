package com.feng.android.lib_framework.data.v2;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 15:26
 * @tips
 * 数据存储的一些规范
 */
public interface IOHandler {
    /*******************save data start***************/
    void save(String key,String value);
    void save(String key,double value);
    void save(String key,int value);
    void save(String key,long value);
    void save(String key,boolean value);
    void save(String key,Object value);
    /*******************save data end***************/

    /*******************get data start***************/
    String getString(String key);
    double getDouble(String key,double defaultValue);
    int getInt(String key,int defaultValue);
    long getLong(String key,long defaultValue);
    boolean getBoolean(String key,boolean defaultValue);
    Object getObject(String key);
    /*******************get data end***************/

}

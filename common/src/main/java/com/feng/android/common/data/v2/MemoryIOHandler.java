package com.feng.android.common.data.v2;

import android.util.LruCache;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 15:28
 * @tips
 */
public class MemoryIOHandler implements IOHandler {
    //存在运行内存里，其实就是Map
    //最好是整个程序运行内存的1/8
    private static LruCache<String,Object> mCache = new LruCache<>(10 * 1024 * 1024);
    @Override
    public void save(String key, String value) {

    }

    @Override
    public void save(String key, double value) {

    }

    @Override
    public void save(String key, int value) {

    }

    @Override
    public void save(String key, long value) {

    }

    @Override
    public void save(String key, boolean value) {

    }

    @Override
    public void save(String key, Object value) {

    }

    @Override
    public String getString(String key) {
        return (String) mCache.get(key);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        return 0;
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return 0;
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return 0;
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return false;
    }

    @Override
    public Object getObject(String key) {
        return null;
    }
}

package com.feng.android.common.data.v2;

import com.feng.android.common.data.v1.PreferenceUtils;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 15:32
 * @tips
 */
public class PreferenceIOHanlder implements IOHandler {
    @Override
    public void save(String key, String value) {
        PreferenceUtils.getInstance().saveString(key,value);
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
        return PreferenceUtils.getInstance().getString(key);
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

package com.feng.android.lib_framework.net.engine.converter;


import com.google.gson.Gson;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-04 15:15
 * @tips
 */
public class GsonConvert implements Converter {
    @Override
    public <T> T convert(String value, Class<T> type) {
        return new Gson().fromJson(value,type);
    }
}

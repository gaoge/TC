package com.feng.android.lib_framework.net.engine;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-12 09:29
 * @tips
 */
public class SPHttpCache {
    public void saveCahce(String finalUrl,String resultJson){
        PreferenceUtil.getInstance().saveParam(finalUrl,resultJson);

    }

    public String getCache(String finalUrl){
        return (String)PreferenceUtil.getInstance().getObject(finalUrl);
    }
}

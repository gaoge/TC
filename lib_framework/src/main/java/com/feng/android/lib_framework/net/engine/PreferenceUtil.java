package com.feng.android.lib_framework.net.engine;

import android.app.Application;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-12 09:33
 * @tips
 */
public class PreferenceUtil {
    private static PreferenceUtil mInstance;


    public static PreferenceUtil getInstance(){
        if(mInstance == null){
            synchronized (PreferenceUtil.class){
                if(null == mInstance){
                    mInstance = new PreferenceUtil();
                }
            }
        }
        return mInstance;
    }

    public void init(Application application){

    }

    public void saveParam(String url,String jsonObj){

    }


    public Object getParam(String url,String defaultValue){
        return "";
    }

    public Object getObject(String url){
        return new Object();
    }


}

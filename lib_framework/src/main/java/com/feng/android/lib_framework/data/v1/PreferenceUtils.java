package com.feng.android.lib_framework.data.v1;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 15:12
 * @tips
 */
public class PreferenceUtils {

    private static PreferenceUtils mInstance;
    private SharedPreferences mPreference;
    private SharedPreferences.Editor mEditor;

    private PreferenceUtils(){

    }

    public void init(Context context){
        mPreference = context.getApplicationContext().getSharedPreferences("cache",Context.MODE_PRIVATE);
        mEditor = mPreference.edit();
    }

    public static PreferenceUtils getInstance(){
        if(mInstance == null){
            synchronized (PreferenceUtils.class){
                if(null == mInstance){
                    mInstance = new PreferenceUtils();
                }
            }
        }
        return mInstance;
    }

    public PreferenceUtils saveString(String key,String value){
        mEditor.putString(key,value);
        return this;
    }

    public String getString(String key){
        return mPreference.getString(key,"");
    }

    public void commit(){
        mEditor.commit();
    }


}

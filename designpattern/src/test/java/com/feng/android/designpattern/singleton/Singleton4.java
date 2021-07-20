package com.feng.android.designpattern.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 17:02
 * @tips
 * 单例设计模式 - 容器管理 系统的服务就是用的这种
 */
public class Singleton4 {
    private static Map<String,Object> mSingleMap = new HashMap<>();
    static {
        mSingleMap.put("activity_manager",new Singleton4());
    }
    private Singleton4(){

    }

    public static Object getService(String serviceName){
        return mSingleMap.get(serviceName);
    }

}

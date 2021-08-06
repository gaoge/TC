package com.feng.android.base.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-06 16:25
 * @tips
 */
public class ReflectUtil {

    /**
     * 
     * @param aClass
     * @param index : 获取第几个泛型信息
     * @return
     */
    public static Class getParameterType(Class aClass,int index){
        Type genericSuperclass = aClass.getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        return (Class) actualTypeArguments[index];
    }
}

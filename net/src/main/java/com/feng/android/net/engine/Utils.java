package com.feng.android.net.engine;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import timber.log.Timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-12 10:42
 * @tips
 */
public class Utils {

    public static String jointParams(String url, Map<String,Object> maps){
        return url;
    }
    public static Class analysisClazzInfo(HttpCallBack callBack){
        Class<? extends HttpCallBack> aClass = callBack.getClass();
        Type genericSuperclass = aClass.getGenericSuperclass();
//        Timber.e("class: " + aClass + ",genericSuperclass: " + genericSuperclass);
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];
//        Class<? extends Type> aClass1 = actualTypeArgument.getClass();
        Class dataclass = (Class) actualTypeArgument;
//        Timber.e("actualTypeArgument: " + actualTypeArgument +",dataclass: " + dataclass);

        return dataclass;
    }
}

package com.feng.android.base.util;

import java.lang.reflect.Array;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-12 18:19
 * @tips
 */
public class ArrayUtil {

    /**
     * 合并数组
     * @param arrayLhs
     * @param arrayRhs
     * @return
     */
    public static Object combineArray(Object arrayLhs,Object arrayRhs){
        Class<?> loadClass = arrayLhs.getClass().getComponentType();
        int i = Array.getLength(arrayLhs);
        int j = i + Array.getLength(arrayRhs);
        Object result = Array.newInstance(loadClass,j);

        for(int k=0;k<j;++k){
            if(k<i){
                Array.set(result,k,Array.get(arrayLhs,k));
            }else{
                Array.set(result,k,Array.get(arrayRhs,k-i));
            }
        }
        return result;
    }
}

package com.feng.android.butterknife_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 09:39
 * @tips
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface WXPayEntry {
    //包名
    String packageName();
    //类的Class
    Class<?> entryClass();
}

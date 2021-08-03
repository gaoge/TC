package com.feng.android.third_framework.retrofit.my;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 17:35
 * @tips
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface POST {
    String value();
}

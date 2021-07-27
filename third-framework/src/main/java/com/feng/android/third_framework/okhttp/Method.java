package com.feng.android.third_framework.okhttp;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-27 12:42
 * @tips
 */
public enum Method {
    POST("POST"),GET("GET"),HEAD("HEAD"),DELETE("DELETE"),PUT("PUT"),PATCH("PATCH");
    String name;

    Method(String name){
        this.name = name;
    }

    public boolean doOutput() {
        switch (this){
            case POST:
            case PUT:
                return true;
        }
        return false;
    }
}

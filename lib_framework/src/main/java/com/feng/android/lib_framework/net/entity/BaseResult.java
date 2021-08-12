package com.feng.android.lib_framework.net.entity;

import android.text.TextUtils;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 15:53
 * @tips
 */
public class BaseResult {
    String code = "-1";
    String msg = "默认错误";

    public boolean isSuccess() {
        return success;
    }

    boolean success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isOk(){
        if(TextUtils.isEmpty(code)){
            return false;
        }
        return "0000".equals(code);
    }
}

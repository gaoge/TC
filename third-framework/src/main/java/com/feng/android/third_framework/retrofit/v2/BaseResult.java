package com.feng.android.third_framework.retrofit.v2;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 15:53
 * @tips
 */
public class BaseResult {
    String code;
    String msg;

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
        return "0000".equals(code);
    }
}

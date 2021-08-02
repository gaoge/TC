package com.feng.android.third_framework.rx.RxLogin;

import java.util.Map;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 11:51
 * @tips
 */
public class RxLoginResult {
    private boolean isSucceed;
    private String msg;
    private Map<String,Object> userInfoMaps;
    private RxLoginPlatform platform;

    public boolean isSucceed() {
        return isSucceed;
    }

    public void setSucceed(boolean succeed) {
        isSucceed = succeed;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getUserInfoMaps() {
        return userInfoMaps;
    }

    public void setUserInfoMaps(Map<String, Object> userInfoMaps) {
        this.userInfoMaps = userInfoMaps;
    }

    public RxLoginPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(RxLoginPlatform platform) {
        this.platform = platform;
    }
}

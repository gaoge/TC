package com.feng.android.designpattern.iterator_pattern.v1;

import java.util.ArrayList;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 16:19
 * @tips
 * QQ 用户系统 - 列表存储
 */
public class QQuserSystem {
    private ArrayList<UserInfo> userInfos = new ArrayList<>();

    public QQuserSystem() {
        userInfos.add(new UserInfo("gg","123456","001","男"));
        userInfos.add(new UserInfo("aa","123456","002","男"));
        userInfos.add(new UserInfo("bb","123456","003","男"));

    }

    public ArrayList<UserInfo> getUserInfos() {
        return userInfos;
    }
}

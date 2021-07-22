package com.feng.android.designpattern.iterator_pattern.v2;

import com.feng.android.designpattern.iterator_pattern.v1.UserInfo;

import java.util.ArrayList;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 16:19
 * @tips
 * QQ 用户系统 - 列表存储
 */
public class QQuserSystem implements Aggregate<UserInfo>{
    private ArrayList<UserInfo> userInfos = new ArrayList<>();

    public QQuserSystem() {
        userInfos.add(new UserInfo("gg","123456","001","男"));
        userInfos.add(new UserInfo("aa","123456","002","男"));
        userInfos.add(new UserInfo("bb","123456","003","男"));

    }

    @Override
    public Iterator<UserInfo> iterator() {
        return new QQIterator(userInfos);
    }
}

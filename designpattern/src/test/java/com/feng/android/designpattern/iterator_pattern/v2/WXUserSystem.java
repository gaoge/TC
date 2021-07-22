package com.feng.android.designpattern.iterator_pattern.v2;

import com.feng.android.designpattern.iterator_pattern.v1.UserInfo;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 16:18
 * @tips
 * 微信的用户系统 - 用户系统 - 数组存储
 */
public class WXUserSystem implements Aggregate<UserInfo> {
    private UserInfo[] userInfos = new UserInfo[3];

    public WXUserSystem() {
        this.userInfos = userInfos;
        userInfos[0] = new UserInfo("cc","123456","001","男");
        userInfos[1] = new UserInfo("dd","123456","002","男");
        userInfos[2] = new UserInfo("ee","123456","003","男");
    }


    @Override
    public Iterator<UserInfo> iterator() {
        return new WXIterator(userInfos);
    }
}

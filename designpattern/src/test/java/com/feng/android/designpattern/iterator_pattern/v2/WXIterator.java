package com.feng.android.designpattern.iterator_pattern.v2;

import com.feng.android.designpattern.iterator_pattern.v1.UserInfo;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 17:10
 * @tips 微信的具体迭代器
 */
public class WXIterator implements Iterator<UserInfo> {
    UserInfo[] userInfos;
    int index = 0;

    public WXIterator(UserInfo[] userInfos) {
        this.userInfos = userInfos;
    }

    @Override
    public UserInfo next() {
        return userInfos[index++];
    }

    @Override
    public boolean hasNext() {
        return index < userInfos.length;
    }
}

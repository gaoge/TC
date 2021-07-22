package com.feng.android.designpattern.iterator_pattern.v2;

import com.feng.android.designpattern.iterator_pattern.v1.UserInfo;

import java.util.List;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 17:10
 * @tips 微信的具体迭代器
 */
public class QQIterator implements Iterator<UserInfo> {
    List<UserInfo> userInfos;
    int index = 0;

    public QQIterator(List<UserInfo>  userInfos) {
        this.userInfos = userInfos;
    }

    @Override
    public UserInfo next() {
        return userInfos.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < userInfos.size();
    }
}

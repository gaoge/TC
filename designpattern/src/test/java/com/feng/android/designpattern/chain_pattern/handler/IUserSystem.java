package com.feng.android.designpattern.chain_pattern.handler;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-22 16:14
 * @tips
 * 校验用户的处理接口
 */
public interface IUserSystem {

    /**
     * 根据用户名，密码查询用户信息
     * @param userName
     * @param userPwd
     * @return
     */
    public UserInfo queryUserInfo(String userName,String userPwd);
}

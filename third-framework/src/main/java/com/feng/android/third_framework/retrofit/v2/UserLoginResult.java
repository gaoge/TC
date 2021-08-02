package com.feng.android.third_framework.retrofit.v2;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-02 15:08
 * @tips
 */
public class UserLoginResult extends BaseResult{
    //成功是一个对象，正常；不成功是一个 String (出错)
//    public UserInfo data;
    public Object data;

    public class UserInfo {
        public String userName;
        public String userPwd;

        @Override
        public String toString() {
            return "UserInfo{" +
                    "userName='" + userName + '\'' +
                    ", userPwd='" + userPwd + '\'' +
                    '}';
        }
    }
}

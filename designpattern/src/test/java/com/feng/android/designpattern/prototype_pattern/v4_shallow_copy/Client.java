package com.feng.android.designpattern.prototype_pattern.v4_shallow_copy;

import org.junit.Test;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 15:02
 * @tips
 */
public class Client {

    @Test
    public void main(){
        User.Address address = new User.Address("立水桥","北京");
        User user = new User("高歌",23,address);

        //浅拷贝
        try {
            //拷贝对象
            User copyUser = user.clone();
            System.out.println("姓名: " + user.userName + ",地址：" + user.userAddress.addressName);
            System.out.println("拷贝姓名: " + copyUser.userName + ",拷贝地址：" + copyUser.userAddress.addressName);

            //把拷贝的地址做修改
            //把copy的地址修改了一下，结果都变成了西二旗，因为这是浅拷贝：类的类对象实例，是没有被拷贝的，而是共用一份
            copyUser.userName = "张三";
            copyUser.userAddress.addressName = "西二旗";

            System.out.println("姓名: " + user.userName + ",地址：" + user.userAddress.addressName);
            System.out.println("拷贝姓名: " + copyUser.userName + ",拷贝地址：" + copyUser.userAddress.addressName);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

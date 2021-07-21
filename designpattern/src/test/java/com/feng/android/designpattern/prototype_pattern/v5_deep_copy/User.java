package com.feng.android.designpattern.prototype_pattern.v5_deep_copy;


/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 15:00
 * @tips
 */
public class User implements Cloneable {

    public String userName;
    public int age;
    public Address userAddress;

    public User(String userName, int age, Address userAddress) {
        this.userName = userName;
        this.age = age;
        this.userAddress = userAddress;
    }

    public static  class Address implements Cloneable{
        public Address(String addressName, String city) {
            this.addressName = addressName;
            this.city = city;
        }

        public String addressName;
        public String city;


        @Override
        protected Address clone() throws CloneNotSupportedException {
            return (Address)super.clone();
        }
    }

    @Override
    protected User clone() throws CloneNotSupportedException {
        User user = (User) super.clone();
        //把地址也做一次深拷贝
        user.userAddress = userAddress.clone();
        return user;
    }
}


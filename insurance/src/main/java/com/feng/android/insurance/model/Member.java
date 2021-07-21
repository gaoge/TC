package com.feng.android.insurance.model;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 17:25
 * @tips
 */
public class Member {
    private String name;
    private String age;

    public Member(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

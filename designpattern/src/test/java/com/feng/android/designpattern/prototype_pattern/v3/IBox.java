package com.feng.android.designpattern.prototype_pattern.v3;

import java.io.IOError;

import androidx.annotation.NonNull;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 14:13
 * @tips
 * 出货的箱子接口
 */
public abstract class IBox implements Cloneable {
    abstract void setNumber(int number);//设置箱子的数量
    abstract  int getNumber();//有多少货

    @Override
    protected IBox clone() throws CloneNotSupportedException {
        return (IBox) super.clone();
    }
}

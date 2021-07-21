package com.feng.android.designpattern.adapter_pattern.v1;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 12:10
 * @tips
 * 只是代表第一个版本的人民币
 */
public class RMBAdapter {
    private float mRmb;

    public RMBAdapter(float rmb){
        this.mRmb = rmb;
    }

    public float getRmb() {
        return mRmb;
    }

    public float getUsd(){
        return mRmb / 5.6f;
    }

}



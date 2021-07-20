package com.feng.android.designpattern.adapterpattern.v3;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 12:17
 * @tips
 * 适配对象 - 把人民币适配成美元
 */
public class Adapter implements UsdTarget{

    private RMBAdapter rmbAdapter;

    public Adapter(RMBAdapter rmbAdapter) {
        this.rmbAdapter = rmbAdapter;
    }

    @Override
    public float getUsd() {
        return rmbAdapter.getRmb() / 5.6f;
    }
}

package com.feng.android.third_framework.okhttp;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-27 13:01
 * @tips
 */
public abstract class NamedRunnable implements Runnable{
    @Override
    public void run() {
        execute();
    }

    protected abstract void execute();
}

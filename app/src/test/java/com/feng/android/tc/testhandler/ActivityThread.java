package com.feng.android.tc.testhandler;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 14:24
 * @tips
 */
public class ActivityThread {

    private H mH = new H();

    public void attach(boolean b) {
        MainActivity mainActivity = new MainActivity();
        mainActivity.onCreate();

        //通过Hanlder去执行Activity的生命周期
        Message message = new Message();
        message.obj = mainActivity;
        mH.sendMessage(message);
    }

    private class H extends Handler{



    }
}

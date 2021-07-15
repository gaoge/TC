package com.feng.android.tc.testhandler;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 14:14
 * @tips
 */
public class TextView {
    private Thread mThread;

    public TextView(){
        mThread = Thread.currentThread();
    }

    public void setText(CharSequence text){
        checkThread();
        System.out.println("更新UI成功: " + text );
    }

    void checkThread(){
        if(mThread != Thread.currentThread()){
            System.out.println("更新UI失败，只能在主线程更新UI: " );

            throw new RuntimeException(
                    "Only the original thread that can created a view hierarchy can touch its views"
            );
        }
    }
}


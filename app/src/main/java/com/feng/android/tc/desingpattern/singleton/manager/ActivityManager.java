package com.feng.android.tc.desingpattern.singleton.manager;

import android.app.Activity;

import com.feng.android.tc.desingpattern.singleton.Singleton2Sync3;

import java.util.Stack;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-15 17:23
 * @tips
 */
public class ActivityManager {
    private volatile static ActivityManager mInstance;

    //集合用谁 List ,LinkedList,Stack,删除和添加比较多
    private Stack<Activity> mActivies = new Stack<>();

    private ActivityManager(){

    }

    public static ActivityManager getInstance(){
        if(null == mInstance){//thread1,thread2
            synchronized (ActivityManager.class){
                if(null == mInstance){
                    mInstance = new ActivityManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 添加统一管理
     * @param activity
     */
    public void attach(Activity activity){
        mActivies.add(activity);

    }

    /**
     * 移除解绑 - 防止内存泄漏
     * @param detachActivity
     */
    public void detach(Activity detachActivity){
        //for去移除有没有问题？一边循环一边移除会出现问题，
        //既然这个写法有问题，自己又想不到什么解决方法，参考一下别人是怎么写的
//        for(Activity activity : mActivies){
//            if(activity == detachActivity){
//                mActivies.remove(activity);
//            }
//        }
        int size = mActivies.size();
        for(int i =0;i<size;i++){
            Activity activity = mActivies.get(i);
            if(activity == detachActivity){
                mActivies.remove(i);
                i--;
                size--;
            }
        }
    }

    /**
     * 关闭当前的Activivity
     * @param finishActivity
     */
    public void finish(Activity finishActivity){
        //for
//        for(Activity activity : mActivies){
//            if(activity == finishActivity){
//                mActivies.remove(activity);
//                finishActivity.finish();
//            }
//        }

        int size = mActivies.size();
        for(int i =0;i<size;i++){
            Activity activity = mActivies.get(i);
            if(activity == finishActivity){
                mActivies.remove(i);
                finishActivity.finish();
                i--;
                size--;
            }
        }
    }

    /**
     * 根据Activity的类名关闭Activity
     * @param activityClass
     */
    public void finish(Class<? extends Activity> activityClass){
        //for
//        for(Activity activity : mActivies){
//            if(activity.getClass().getCanonicalName().endsWith(activityClass.getCanonicalName())){
//                mActivies.remove(activity);
//                activity.finish();
//            }
//        }

        int size = mActivies.size();
        for(int i =0;i<size;i++){
            Activity activity = mActivies.get(i);
            if(activity.getClass().getCanonicalName().endsWith(activityClass.getCanonicalName())){
                mActivies.remove(i);
                activity.finish();
                i--;
                size--;
            }
        }
    }

    /**
     * 退出整个应用
     */
    public void exitApplication(){

    }

    public Activity currentActivity(){
        return mActivies.lastElement();
    }

}

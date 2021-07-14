package com.feng.android.butterknife;

import android.app.Activity;

import java.lang.reflect.Constructor;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-13 16:10
 * @tips
 */
public class Butterknife {
    public static Unbinder bind(Activity activity){
        //xxxActivity_ViewBinding viewBinding = new xxxActivity_ViewBinding(this);
        String className  = activity.getClass().getName() + "_ViewBinding";
        try {
            Class<? extends Unbinder> clazz = (Class<? extends Unbinder>) Class.forName(className);
            Constructor<? extends Unbinder> constructor = clazz.getDeclaredConstructor(activity.getClass());
            Unbinder unbinder = constructor.newInstance(activity);
            return unbinder;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unbinder.EMPTY;
    }
}

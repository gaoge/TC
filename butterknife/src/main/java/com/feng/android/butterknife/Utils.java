package com.feng.android.butterknife;

import android.app.Activity;
import android.view.View;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-14 11:49
 * @tips
 */
public class Utils {
    public static <T extends View> T findViewById(Activity activity, int viewId){
        return activity.findViewById(viewId);
    }
}

package com.feng.android.designpattern.bridge_pattern;

import androidx.annotation.NonNull;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-23 13:34
 * @tips
 */
public class Original implements CoffeeAdditives{

    @Override
    public String toString() {
        return "原味";
    }
}

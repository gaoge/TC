package com.feng.android.common.ui.navigationBar;

import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 10:40
 * @tips
 * 这个是可以拿过来直接使用的导航栏
 *
 * //在写代码的时候一个是高扩展 ，并不是要把所有的内容和出现的问题都想到，
 * 而是在新增加功能的时候可以保证原来的代码不变动，对于开发者来说需要用好 最少知识原则，使用者是并不想关注太多
 */
public class NavigationBar extends AbsNavigationBar{
    private Builder mBuilder;

    protected NavigationBar(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbsNavigationBar.Builder<NavigationBar.Builder>{

        public Builder(Context context, int layoutId, ViewGroup parent) {
            super(context, layoutId, parent);
        }

        @Override
        public NavigationBar create() {
            return new NavigationBar(this);
        }
    }
}

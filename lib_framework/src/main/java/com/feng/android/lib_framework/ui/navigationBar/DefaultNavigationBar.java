package com.feng.android.lib_framework.ui.navigationBar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feng.android.lib_framework.R;


/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 10:40
 * @tips
 * 可以直接拿过来使用的默认样式导航栏
 */
public class DefaultNavigationBar extends AbsNavigationBar<DefaultNavigationBar.Builder>{
    private Builder mBuilder;

    protected DefaultNavigationBar(Builder builder) {
        super(builder);
    }

    @Override
    public void attachNavigationParams() {
        super.attachNavigationParams();

        TextView leftView = findViewById(R.id.back_tv);
        leftView.setVisibility(getBuilder().mLeftVisible);

    }

    public static class Builder extends AbsNavigationBar.Builder<DefaultNavigationBar.Builder>{

        public int mLeftVisible = View.VISIBLE;
        public Builder(Context context,  ViewGroup parent) {
            super(context, R.layout.ui_default_navigation_bar, parent);
        }

        @Override
        public DefaultNavigationBar create() {
            return new DefaultNavigationBar(this);
        }

        public Builder setLeftText(String text){
            setText(R.id.back_tv,text);
            return this;
        }

        public Builder setLeftClickListener(View.OnClickListener clickListener){
            setOnClickListener(R.id.back_tv,clickListener);
            return this;
        }

        public Builder hideLeftText(){
            mLeftVisible = View.INVISIBLE;
            return this;
        }
    }
}

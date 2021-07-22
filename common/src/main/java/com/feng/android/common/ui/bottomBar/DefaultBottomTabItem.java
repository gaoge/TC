package com.feng.android.common.ui.bottomBar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorStateListDrawable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.feng.android.common.R;


/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-21 17:56
 * @tips
 * 这个要自己写
 */
public class DefaultBottomTabItem extends AbsBottomTabItem {

    private Builder builder;
    private DefaultBottomTabItem(Context context) {
        super(R.layout.default_bottom_tab_item, context);
    }

    public DefaultBottomTabItem(Builder builder) {
        this(builder.context);
        this.builder = builder;
    }

    @Override
    protected void initLayout() {
        //初始化显示
        TextView tabText = findViewById(R.id.tab_text);
        ImageView tabIcon = findViewById(R.id.tab_icon);

        if(!TextUtils.isEmpty(builder.text)){
            tabText.setText(builder.text);
        }

        if(builder.textColorState != 0){
            tabText.setTextColor(builder.context.getResources().getColorStateList(builder.textColorState));
        }
        if(builder.resIconId != 0){
            tabIcon.setImageResource(builder.resIconId);
        }
    }

    @Override
    protected void setSelected(boolean selected) {
        TextView tabText = findViewById(R.id.tab_text);
        ImageView tabIcon = findViewById(R.id.tab_icon);

        tabText.setSelected(selected);
        tabIcon.setSelected(selected);
    }

    public static class Builder{
        Context context;
        String text;
        int resIconId;
        int textColorState;

        public Builder(Context context){
            this.context = context;
        }

        public Builder text(String text){
            this.text = text;
            return this;
        }

        public Builder textColor(int textColorState){
            this.textColorState = textColorState;
            return this;
        }

        public Builder resIconId(int resIconId){
            this.resIconId = resIconId;
            return this;
        }

        public DefaultBottomTabItem create(){
            return new DefaultBottomTabItem(this);
        }




    }
}

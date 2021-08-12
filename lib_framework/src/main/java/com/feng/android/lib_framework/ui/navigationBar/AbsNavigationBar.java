package com.feng.android.lib_framework.ui.navigationBar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-16 10:36
 * @tips
 * 这个是导航栏的基类
 */
public class AbsNavigationBar<B extends AbsNavigationBar.Builder> implements INavigation{

    private B mBuilder;
    private View mNavigationBar;

    protected AbsNavigationBar(B builder){
        this.mBuilder = builder;
        createNavigationBar();
    }

    @Override
    public void createNavigationBar() {
        mNavigationBar = LayoutInflater.from(mBuilder.mContext)
                .inflate(mBuilder.mLayoutId,mBuilder.mParent,false);
        //添加
        attachParent(mNavigationBar,mBuilder.mParent);
        //绑定参数
        attachNavigationParams();
        
    }

    /**
     * 绑定参数
     */
    @Override
    public void attachNavigationParams() {
        //设置文本
        Map<Integer,CharSequence> textMaps = mBuilder.mTextMaps;
        for(Map.Entry<Integer,CharSequence> entry:textMaps .entrySet()){
            TextView textView = findViewById(entry.getKey());
            textView.setText(entry.getValue());
        }

        //设置点击事件
        Map<Integer,View.OnClickListener> listenerMaps = mBuilder.mClickListenerMaps;
        for(Map.Entry<Integer,View.OnClickListener> entry:listenerMaps.entrySet()){
            View view = findViewById(entry.getKey());
            view.setOnClickListener(entry.getValue());
        }
    }

    public <T extends View> T findViewById(Integer viewId) {
        return mNavigationBar.findViewById(viewId);
    }

    /**
     * 将NavigationView添加到父布局
     */
    @Override
    public void attachParent(View navigationBar, ViewGroup parent) {
        parent.addView(navigationBar,0);
    }

    /**
     * Builder 构建类
     * 构建 NavigationBar 还有存储参数
     */
    public static abstract class Builder<B extends Builder>{
        public Context mContext;
        public int mLayoutId;
        public ViewGroup mParent;
        public Map<Integer,CharSequence> mTextMaps;
        public Map<Integer,View.OnClickListener> mClickListenerMaps;

        public Builder(Context context,int layoutId, ViewGroup parent){
            this.mContext = context;
            this.mLayoutId = layoutId;
            this.mParent = parent;
            mTextMaps = new HashMap<>();
            mClickListenerMaps = new HashMap<>();
        }

        /**
         * 用来创建AbsNavigationBar
         * @return
         */
        public abstract AbsNavigationBar create();

        //返回的是AbsNavigationBar 的 Builder，但是当我们调用create()方法的时候会报错
        public B setText(int viewId, String text){
            mTextMaps.put(viewId,text);
            return (B) this;
        }

        public B setOnClickListener(int viewId,View.OnClickListener clickListener){
            mClickListenerMaps.put(viewId,clickListener);
            return (B) this;
        }

    }

    public B getBuilder() {
        return mBuilder;
    }
}

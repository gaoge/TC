package com.feng.android.lib_framework.ui.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-13 14:30
 * @tips
 * Dialog View 的辅助处理类
 */
class DialogViewHelper {

    private View mContentView;

    //防止霸气侧漏
    private SparseArray<WeakReference<View>> mViews = new SparseArray<>();

    public DialogViewHelper(Context context, int viewLayoutResId) {
        mContentView = LayoutInflater.from(context).inflate(viewLayoutResId,null,false);
    }

    public DialogViewHelper() {

    }

    public void setContentView(View mView) {
        this.mContentView = mView;
    }

    /**
     * 设置文本
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text) {
        //每次 findViewById  减少 findViewbyId 参数
        TextView textView = getView(viewId);
        if(null != textView){
            textView.setText(text);
        }

    }

    public <T extends View> T getView(int viewId) {
        WeakReference<View> viewWeakReference = mViews.get(viewId);
        //测漏的问题
        View view = null;
        if(null != viewWeakReference){
            view =  viewWeakReference.get();
        }
        if(null == view){
            view = mContentView.findViewById(viewId);
            if(null != view){
                mViews.put(viewId,new WeakReference<>(view));
            }
        }
        return (T) view;
    }

    /**
     * 设置点击事件
     * @param viewId
     * @param clickListener
     */
    public void setClickListener(int viewId, View.OnClickListener clickListener) {
        View view = getView(viewId);
        if(null != view){
            view.setOnClickListener(clickListener);
        }
    }

    public View getContentView() {
        return mContentView;
    }
}

package com.feng.android.lib_framework.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.feng.android.lib_framework.R;

import androidx.annotation.NonNull;


/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-13 14:29
 * @tips
 * 自定义万能 Dialog
 */
public class DefaultDialog extends Dialog {

    private AlertController mAlertController;


    public DefaultDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mAlertController = new AlertController(this,getWindow());
    }

    public <T extends View> T getView(int viewId){
        return mAlertController.getView(viewId);
    }

    /**
     * 设置点击事件
     * @param viewId
     * @param clickListener
     */
    public void setClickListener(int viewId, View.OnClickListener clickListener) {
        mAlertController.setClickListener(viewId,clickListener);
    }

    public static class Builder{
        private final AlertController.AlertParams P;

        public Builder(Context context){
            this(context, R.style.dialog);
        }

        public Builder(Context context,int themeResId){
            P = new AlertController.AlertParams(context,themeResId);
        }

        public Builder setContentView(View view){
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }

        //设置布局
        public Builder setContentView(int layoutId){
            P.mView = null;
            P.mViewLayoutResId = layoutId;
            return this;
        }

        //设置Text 元素 文本
        public Builder setText(int viewId,CharSequence text){
            P.mTextArray.put(viewId,text);
            return this;
        }

        //设置UI元素点击事件
        public Builder setOnClickListener(int viewId,View.OnClickListener clickListener){
            P.mClickArray.put(viewId,clickListener);
            return this;
        }

        public Builder setCancelable(boolean cancelable){
            P.mCancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener){
            P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismisslListener(OnDismissListener onDismisslListener){
            P.mOnDismissLiestener = onDismisslListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener){
            P.mOnKeyListener = onKeyListener;
            return this;
        }

        //配置一些万能的参数
        public Builder fullWidth(){
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        /**
         * 从底部弹出
         * @param isAnimation 是否有动画
         * @return
         */
        public Builder fromBottom(boolean isAnimation){
            if(isAnimation){
                P.mAnimation = R.style.dialog_from_bottom_anim;
            }
            P.mGravity = Gravity.BOTTOM;
            return this;
        }

        /**
         * 设置Dialog 宽，高
         * @param width
         * @param height
         * @return
         */
        public Builder setWidthAndHeight(int width,int height){
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }

        /**
         * 添加默认动画
         * @return
         */
        public Builder addDefaultAnimation(){
            P.mAnimation = R.style.dialog_scale_anim;
            return this;
        }

        /**
         * 设置动画
         * @param styleAnimation
         * @return
         */
        public Builder addAnimations(int styleAnimation){
            P.mAnimation = styleAnimation;
            return this;
        }

        public DefaultDialog create(){
            final DefaultDialog dialog = new DefaultDialog(P.mContext,P.mThemeResId);
            P.apply(dialog.mAlertController);
            dialog.setCancelable(P.mCancelable);
            if(P.mCancelable){
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissLiestener);
            if(P.mOnKeyListener != null){
                dialog.setOnKeyListener(P.mOnKeyListener);
            }


            return dialog;
        }

        public DefaultDialog show() {
            DefaultDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }


}

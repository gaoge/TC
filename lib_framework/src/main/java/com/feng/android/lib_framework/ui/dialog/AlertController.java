package com.feng.android.lib_framework.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-08-13 14:30
 * @tips
 */
class AlertController {

    private DefaultDialog mAlertDialog;
    private Window mWindow;
    private DialogViewHelper mViewHelper;

    public AlertController(DefaultDialog alertDialog, Window window) {
        this.mAlertDialog = alertDialog;
        this.mWindow = window;
    }

    public void setViewHelper(DialogViewHelper mViewHelper) {
        this.mViewHelper = mViewHelper;
    }

    /**
     * 设置文本
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text) {
        mViewHelper.setText(viewId,text);
    }

    public <T extends View> T getView(int viewId) {
        return mViewHelper.getView(viewId);
    }

    /**
     * 设置点击事件
     * @param viewId
     * @param clickListener
     */
    public void setClickListener(int viewId, View.OnClickListener clickListener) {
        mViewHelper.setClickListener(viewId,clickListener);
    }

    /**
     * 获取 dialog
     * @return
     */
    public DefaultDialog getAlertDialog() {
        return mAlertDialog;
    }

    /**
     * 获取 Dialog 的 window
     * @return
     */
    public Window getWindow() {
        return mWindow;
    }

    public static class AlertParams{
        public Context mContext;
        public int mThemeResId;
        //点击空白是否能够取消
        public boolean mCancelable = true;
        //dialog 消失监听
        public DialogInterface.OnCancelListener mOnCancelListener;
        //dialog cancel监听
        public DialogInterface.OnDismissListener mOnDismissLiestener;
        //dialog 按键监听
        public DialogInterface.OnKeyListener mOnKeyListener;
        //布局的view
        public View mView;
        //布局的layoutId
        public int mViewLayoutResId;
        //宽度
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        //高度
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        //动画
        public int mAnimation = 0;
        //位置
        public int mGravity = Gravity.CENTER;

        //存放字体的修改
        SparseArray<CharSequence> mTextArray = new SparseArray<>();
        //存放点击事件
        SparseArray<View.OnClickListener> mClickArray = new SparseArray<>();


        public AlertParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;
        }

        public void apply(AlertController mAlertController) {
            //1. 设置布局 DailogViewHelper
            DialogViewHelper dialogViewHelper = null;
            if(mViewLayoutResId != 0){
                dialogViewHelper = new DialogViewHelper(mContext,mViewLayoutResId);
            }

            if(mView != null){
                dialogViewHelper = new DialogViewHelper();
                dialogViewHelper.setContentView(mView);
            }

            mAlertController.setViewHelper(dialogViewHelper);

            if(null == dialogViewHelper){
                throw new IllegalArgumentException("请设置布局 setContenview");
            }

            mAlertController.getAlertDialog().setContentView(dialogViewHelper.getContentView());

            //2. 设置文本
            int textArraySize = mTextArray.size();
            for(int i =0;i<textArraySize;i++){
                mAlertController.setText(mTextArray.keyAt(i),mTextArray.valueAt(i));
            }

            //3. 设置点击
            int clickArraySize = mClickArray.size();
            for(int i=0;i<clickArraySize;i++){
                mAlertController.setClickListener(mClickArray.keyAt(i),mClickArray.valueAt(i));
            }

            //4. 设置自定义的效果 全屏 从底部弹出 默认动画
            Window window = mAlertController.getWindow();
            //4.1 设置位置
            window.setGravity(mGravity);

            //4.2 设置动画
            if(0 != mAnimation){
                window.setWindowAnimations(mAnimation);
            }

            //4.3 设置宽高
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = mWidth;
            params.height = mHeight;
            window.setAttributes(params);

        }
    }
}

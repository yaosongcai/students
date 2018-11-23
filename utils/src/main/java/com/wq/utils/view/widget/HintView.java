package com.wq.utils.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wq.utils.R;
import com.wq.utils.util.SizeUtils;

/**
 * 一个轻量级的progressBar消息进度弹窗
 */
public class HintView {

    private TextView mTvHint;
    private Dialog mDialog;
    private boolean canClick;

    /**
     * api>=21
     * hintview的构造方法
     * @param context
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HintView(Context context) {
        super();
        canClick = true;
        ProgressView v = new ProgressView(context);
        mDialog = new MyDialog(context, R.style.dialog);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(v);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * hintview是否在显示
     * @return  true  false
     */
    public boolean isShowing() {

        if (mDialog != null) {
            return mDialog.isShowing();
        }
        return false;
    }

    class ProgressView extends LinearLayout {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public ProgressView(Context context) {
            super(context);

            setOrientation(HORIZONTAL);
            setGravity(Gravity.CENTER);
            setPadding(SizeUtils.dp2px(10), SizeUtils.dp2px(10), SizeUtils.dp2px(10), SizeUtils.dp2px(10));
            setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            setChildView(context);
        }

        public ProgressView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private void setChildView(Context context) {
            ProgressBar bar = new ProgressBar(context, null, 0, R.style.dialog_rotate);
            addView(bar);
            bar.getLayoutParams().height = bar.getLayoutParams().width = SizeUtils.dp2px(30);

            mTvHint = new TextView(context);
            mTvHint.setText("加载中，请稍候...");
            mTvHint.setTextColor(Color.parseColor("#585858"));
            mTvHint.setTextSize(SizeUtils.sp2px(15));
            mTvHint.setGravity(Gravity.CENTER);
            addView(mTvHint);
            mTvHint.getLayoutParams().height = mTvHint.getLayoutParams().height = LayoutParams.WRAP_CONTENT;
            ((LayoutParams) mTvHint.getLayoutParams()).setMargins(SizeUtils.dp2px(5), SizeUtils.dp2px(5), SizeUtils.dp2px(5), SizeUtils.dp2px(5));
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
        }
    }

    class MyDialog extends Dialog {

        public MyDialog(Context context, int dialog) {
            super(context, dialog);
        }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && canClick) { //监控/拦截/屏蔽返回键
                mDialog.dismiss();
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_MENU) {
                //监控/拦截菜单键
            } else if (keyCode == KeyEvent.KEYCODE_HOME) {
                //由于Home键为系统键，此处不能捕获，需要重写onAttachedToWindow()
            }
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * 返回textview
     * @return  textview
     */
    public TextView getmTvHint() {
        return mTvHint;
    }

    /**
     * 设置是否能够点击
     * @param canClick  是否能点击
     */
    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    /**
     * 添加textview
     * @param mTvHint
     * @return
     */
    public HintView setmTvHint(TextView mTvHint) {
        this.mTvHint = mTvHint;
        return this;
    }

    /**
     * 设置是否能点击
     * @param flag
     * @return
     */
    public HintView setCancelable(boolean flag) {
        mDialog.setCancelable(flag);
        return this;
    }

    /**
     * 设置消息
     * @param msg
     * @return
     */
    public HintView setMessage(String msg) {
        mTvHint.setText(msg);
        return this;
    }

    /**
     * 开始显示
     */
    public void show() {

        try {
            mDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 结束显示
     */
    public void dismiss() {
        try {
            mDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置关闭显示回调
     * @param listener  监听器
     */
    public void setOnDismissListener(OnDismissListener listener) {
        mDialog.setOnDismissListener(listener);
    }

}

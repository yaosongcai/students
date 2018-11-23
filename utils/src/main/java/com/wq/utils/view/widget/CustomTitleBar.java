package com.wq.utils.view.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wq.utils.R;
import com.wq.utils.util.BarUtils;
import com.wq.utils.util.SizeUtils;

/**
 * 项目名称：    com.wq.utils.view.widget
 * 类描述
 * <pre>
 *     自定义标题栏,定义属性{@link LayoutParams}
 *     layout attributes
 *     @attr-customTitleBar ref R.styleable#CustomTitleBar_title_background         titleBar背景
 *     @attr-customTitleBar ref R.styleable#CustomTitleBar_title_text               title标题
 *     @attr-customTitleBar ref R.styleable#CustomTitleBar_title_textColor          title标题颜色
 *     @attr-customTitleBar ref R.styleable#CustomTitleBar_title_textSize           title标题尺寸
 *     @attr-customTitleBar ref R.styleable#CustomTitleBar_left_button_image        title左边按钮图片
 *     @attr-customTitleBar ref R.styleable#CustomTitleBar_left_button_text         title左边文字
 *     @attr-customTitleBar ref R.styleable#CustomTitleBar_left_button_textColor    title左边文字颜色
 *     @attr-customTitleBar ref R.styleable#CustomTitleBar_left_button_textSize     title左边文字尺寸
 *     @attr-customTitleBar ref R.styleable#CustomTitleBar_show_left_button         title是否显示左边按钮或文字
 *     @attr-customTitleBar ref R.styleable#CustomTitleBar_right_button_image       title右边按钮图片
 *     @attr-customTitleBar ref R.styleable#CustomTitleBar_right_button_text        title右边文字
 *     @attr-customTitleBar ref R.styleable#CustomTitleBar_right_button_textColor   title右边文字颜色
 *     @attr-customTitleBar ref R.styleable#CustomTitleBar_right_button_textSize    title右边文字尺寸
 *     @attr-customTitleBar ref R.styleable#CustomTitleBar_show_right_button        title是否显示右边按钮或文字
 *     @attr-customTitleBar ref R.styleable#CustomTitleBar_show_view_status         title是否添加状态栏高度      默认是
 * </pre>
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/9/28
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/9/28
 * 修改备注：    2018-10-19 完善注释
 */
public class CustomTitleBar extends RelativeLayout {

    private Context mContext;

    private LayoutParams params;

    private TextView mTitle;

    private TextView mRightText;

    private ImageView mRightImg;

    private TextView mLeftText;

    private ImageView mLeftImg;

    private View mStatusView;

    private String height;


    /**
     * 标题的点击事件
     */
    private TitleOnClickListener titleOnClickListener;

    /**
     * 构造方法
     *
     * @param context
     */
    public CustomTitleBar(Context context) {
        this(context, null);
    }

    /**
     * 构造方法
     *
     * @param context
     * @param attrs
     */
    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;

        params = new LayoutParams();

        obtainAttributes(context, attrs);

        setChildView();

    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        height = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_height");
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.CustomTitleBar_title_background) {
                params.mBackgroundColor = typedArray.getColor(attr, Color.WHITE);
            } else if (attr == R.styleable.CustomTitleBar_title_text) {
                params.mText = typedArray.getString(attr);
            } else if (attr == R.styleable.CustomTitleBar_title_textColor) {
                params.mTextColor = typedArray.getColor(attr, Color.BLACK);
            } else if (attr == R.styleable.CustomTitleBar_title_textSize) {
                params.mTextSize = typedArray.getDimensionPixelSize(attr, 20);
            } else if (attr == R.styleable.CustomTitleBar_left_button_image) {
                params.mLeftImageResId = typedArray.getResourceId(attr, R.drawable.back);
            } else if (attr == R.styleable.CustomTitleBar_left_button_text) {
                params.mLeftText = typedArray.getString(attr);
            } else if (attr == R.styleable.CustomTitleBar_left_button_textColor) {
                params.mLeftTextColor = typedArray.getColor(attr, Color.WHITE);
            } else if (attr == R.styleable.CustomTitleBar_left_button_textSize) {
                params.mLeftTextSize = typedArray.getDimensionPixelSize(attr, 20);
            } else if (attr == R.styleable.CustomTitleBar_show_left_button) {
                params.mShowDisplayBack = typedArray.getBoolean(attr, true);
            } else if (attr == R.styleable.CustomTitleBar_right_button_image) {
                params.mRightImageResId = typedArray.getResourceId(attr, 0);
            } else if (attr == R.styleable.CustomTitleBar_right_button_text) {
                params.mRightText = typedArray.getString(attr);
            } else if (attr == R.styleable.CustomTitleBar_right_button_textColor) {
                params.mRightTextColor = typedArray.getColor(attr, Color.WHITE);
            } else if (attr == R.styleable.CustomTitleBar_right_button_textSize) {
                params.mRightTextSize = typedArray.getDimensionPixelSize(attr, 14);
            } else if (attr == R.styleable.CustomTitleBar_show_right_button) {
                params.mShowDisplaySave = typedArray.getBoolean(attr, true);
            } else if (attr == R.styleable.CustomTitleBar_show_view_status) {
                params.mShowDisplayStatus = typedArray.getBoolean(attr, true);
            }
        }

        typedArray.recycle();
    }

    private void setChildView() {

        if (params.mTextSizePaint == null) params.mTextSizePaint = new Paint();


        if (params.mShowDisplayStatus) {
            setStatusView(params.mShowDisplayStatus);
        }

        if (params.mBackgroundColor != 0) {
            setBackgrounds(params.mBackgroundColor);
        }

        if (params.mText != null) {
            setTitleText(params.mText);
        }

        if (params.mTextColor != 0) {
            setTextColor(params.mTextColor);
        }

        if (params.mTextSize != 0) {
            setTextSize(params.mTextSize);
        } else {
            setTextSize(20);
        }

        if (params.mLeftText != null) {
            setLeftText(params.mLeftText);
        }

        if (params.mLeftTextColor != 0) {
            setLeftTextColor(params.mLeftTextColor);
        }

        if (params.mLeftTextSize != 0) {
            setLeftTextSize(params.mLeftTextSize);
        }

        if (params.mLeftText == null) {
            if (params.mLeftImageResId != 0) {
                setLeftImage(params.mLeftImageResId);
            } else {
                setLeftImage(R.drawable.back);
            }
        }

        if (params.mRightImageResId != 0) {
            setRightImage(params.mRightImageResId);
        }

        if (params.mRightText != null) {
            setRightText(params.mRightText);
        }

        if (params.mRightTextColor != 0) {
            setRightTextColor(params.mRightTextColor);
        }

        if (params.mRightTextSize != 0) {
            setRightTextSize(params.mRightTextSize);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        params.mViewStatusHeight = params.mShowDisplayStatus ? BarUtils.getStatusHeight(mContext) : 0;
        int height = SizeUtils.dp2px(mContext, 48);
        params.mHeight = height + params.mViewStatusHeight;
        params.mWidth = MeasureSpec.getSize(widthMeasureSpec);

        setMeasuredDimension(params.mWidth, params.mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view == mTitle) {
                view.layout((int) (params.mWidth / 2 - (params.mStrWidth / 2)), (int) ((params.mViewStatusHeight + ((params.mHeight - params.mViewStatusHeight) / 2)) - (params.mStrHeight / 2)), (int) (params.mWidth / 2 + (params.mStrWidth / 2)), (int) ((params.mViewStatusHeight + ((params.mHeight - params.mViewStatusHeight) / 2)) + (params.mStrHeight / 2)));
            } else if (view == mLeftImg) {
                view.layout(0, params.mViewStatusHeight, (int) (params.mHeight - params.mViewStatusHeight), params.mHeight);
            } else if (view == mLeftText) {
                view.layout(SizeUtils.dp2px(mContext, 12), (int) ((params.mViewStatusHeight + ((params.mHeight - params.mViewStatusHeight) / 2)) - (params.mLeftStrHeight / 2)), (int) (SizeUtils.dp2px(mContext, 12) + params.mLeftStrWidth), (int) ((params.mViewStatusHeight + ((params.mHeight - params.mViewStatusHeight) / 2)) + (params.mLeftStrHeight / 2)));
            } else if (view == mRightImg) {
                view.layout(params.mWidth - params.mHeight - params.mViewStatusHeight - SizeUtils.dp2px(mContext, 10), params.mViewStatusHeight, params.mWidth - SizeUtils.dp2px(mContext, 10), params.mHeight);
            } else if (view == mRightText) {
                view.layout((int) (params.mWidth - SizeUtils.dp2px(mContext, 12) - params.mRightStrWidth), (int) ((params.mViewStatusHeight + ((params.mHeight - params.mViewStatusHeight) / 2)) - (params.mRightStrHeight / 2)), params.mWidth - SizeUtils.dp2px(mContext, 12), (int) ((params.mViewStatusHeight + ((params.mHeight - params.mViewStatusHeight) / 2)) + (params.mRightStrHeight / 2)));
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 设置是否添加状态栏高度
     *
     * @param isDisplay true-是      false-否
     * @author ysc
     * @time 2018/9/29 9:41
     */
    public void setStatusView(boolean isDisplay) {
        params.mShowDisplayStatus = isDisplay;
        if (!isDisplay && mStatusView == null) {
            return;
        }

        if (!isDisplay && mStatusView != null) {
            this.removeView(mStatusView);
            return;
        }

        if (isDisplay && mStatusView == null) {
            initStatusView();
        }
    }

    /**
     * <p>
     * 是否显示返回键或者返回按钮
     * </p>
     *
     * @param isDisplay
     * @author ysc
     * @time 2018/10/30 15:42
     */
    public void setDisplayBack(boolean isDisplay) {
        params.mShowDisplayBack = isDisplay;
        if (!isDisplay && mLeftImg == null && mLeftText == null) {
            return;
        }

        if (!isDisplay && mLeftImg != null) {
            this.removeView(mLeftImg);
        }

        if (!isDisplay && mLeftText != null) {
            this.removeView(mLeftText);
        }

        setChildView();
    }

    /**
     * <p>
     *     是否显示右边按钮
     * </p>
     * @param isDisplay
     * @author ysc
     * @time 2018/10/30 15:52
     */
    public void setDisplaySave(boolean isDisplay) {
        params.mShowDisplaySave = isDisplay;
        if (!isDisplay && mRightImg == null && mRightText == null){
            return;
        }

        if (!isDisplay && mRightImg != null){
            this.removeView(mRightImg);
        }

        if (!isDisplay && mRightText != null){
            this.removeView(mRightText);
        }

        setChildView();
    }

    /**
     * <pre>
     *     设置title标题
     * </pre>
     *
     * @param title 标题
     * @author ysc
     * @time 2018/9/28 17:01
     */
    public void setTitleText(@IdRes int title) {
        if (mTitle == null) {
            initTitleText();
        }

        mTitle.setText(title);
        measureText(mContext.getResources().getString(title), mTitle.getTextSize(), 1);
    }

    public void setBackgrounds(int color) {
        setBackgroundColor(color);
    }

    /**
     * <pre>
     *     设置title标题
     * </pre>
     *
     * @param title 标题
     * @author ysc
     * @time 2018/9/28 16:58
     */
    public void setTitleText(String title) {
        if (mTitle == null) {
            initTitleText();
        }
        mTitle.setText(title);
        measureText(title, mTitle.getTextSize(), 1);
    }

    /**
     * <pre>
     *     设置title标题颜色
     * </pre>
     *
     * @param color 颜色
     * @author ysc
     * @time 2018/9/28 17:03
     */
    public void setTextColor(int color) {
        if (mTitle == null) {
            initTitleText();
        }
        mTitle.setTextColor(color);
    }

    /**
     * <pre>
     *     设置title标题字体大小
     * </pre>
     *
     * @param size 尺寸(sp)
     * @author ysc
     * @time 2018/9/28 17:03
     */
    public void setTextSize(float size) {
        if (mTitle == null) {
            initTitleText();
        }
        mTitle.setTextSize(size);
        measureText(mTitle.getText().toString(), mTitle.getTextSize(), 1);
    }

    /**
     * <pre>
     *     设置title左标题
     * </pre>
     *
     * @param lefttext 左标题
     * @author ysc
     * @time 2018/9/28 17:03
     */
    public void setLeftText(String lefttext) {

        if (!params.mShowDisplayBack) return;

        if (mLeftText == null) {
            initLeftText();
        }

        if (mLeftImg != null) {
            mLeftImg.setVisibility(GONE);
        }

        mLeftText.setText(lefttext);

        measureText(lefttext, mLeftText.getTextSize(), 0);
    }

    /**
     * <pre>
     *     设置title左标题字体颜色
     * </pre>
     *
     * @param color 做标题颜色
     * @author ysc
     * @time 2018/9/28 17:03
     */
    public void setLeftTextColor(int color) {

        if (!params.mShowDisplayBack) return;

        if (mLeftText == null) {
            initLeftText();
        }
        mLeftText.setTextColor(color);
    }

    /**
     * <pre>
     *     设置title左标题
     * </pre>
     *
     * @param size 左标题尺寸
     * @author ysc
     * @time 2018/9/28 17:03
     */
    public void setLeftTextSize(float size) {

        if (!params.mShowDisplayBack) return;

        if (mLeftText == null) {
            initLeftText();
        }

        mLeftText.setTextSize(size);
        measureText(mLeftText.getText().toString(), mLeftText.getTextSize(), 0);
    }

    /**
     * <pre>
     *     设置title返回按钮图片
     * </pre>
     *
     * @param resId 左按钮图片
     * @author ysc
     * @time 2018/9/28 17:03
     */
    public void setLeftImage(int resId) {

        if (!params.mShowDisplayBack) return;

        if (mLeftImg == null) {
            initLeftImage();
        }

        mLeftImg.setImageResource(resId);
    }

    /**
     * <pre>
     *     设置title右边图片
     * </pre>
     *
     * @param resId 右按钮图片
     * @author ysc
     * @time 2018/9/28 17:03
     */
    public void setRightImage(int resId) {

        if (!params.mShowDisplaySave) return;

        if (mRightImg == null) {
            initRightImage();
        }

        mRightImg.setImageResource(resId);
    }

    /**
     * <pre>
     *     设置title右边标题
     * </pre>
     *
     * @param text 右标题
     * @author ysc
     * @time 2018/9/28 17:03
     */
    public void setRightText(String text) {

        if (!params.mShowDisplaySave) return;

        if (mRightText == null) {
            initRightText();
        }

        mRightText.setText(text);

        measureText(text, mRightText.getTextSize(), 2);
    }

    /**
     * <pre>
     *     设置title右边标题颜色
     * </pre>
     *
     * @param color 右标题颜色
     * @author ysc
     * @time 2018/9/28 17:03
     */
    public void setRightTextColor(int color) {

        if (!params.mShowDisplaySave) return;

        if (mRightText == null) {
            initRightText();
        }

        mRightText.setTextColor(color);
    }

    /**
     * <pre>
     *     设置title右边标题大小
     * </pre>
     *
     * @param size 右标题尺寸
     * @author ysc
     * @time 2018/9/28 17:03
     */
    public void setRightTextSize(float size) {

        if (!params.mShowDisplaySave) return;

        if (mRightText == null) {
            initRightText();
        }

        mRightText.setTextSize(size);
        measureText(mRightText.getText().toString(), mRightText.getTextSize(), 2);
    }

    /**
     * 获取左边的返回按钮
     *
     * @return Button   返回左按钮组件
     */
    public ImageView getLeft_button() {
        return mLeftImg;
    }

    /**
     * 获取标题栏标题TextView
     *
     * @return TextView
     */
    public TextView getTitle() {
        return mTitle;
    }

    /**
     * 获取右边的保存按钮
     *
     * @return Button       返回右按钮组件
     */
    public TextView getRight_button() {
        return mRightText;
    }

    private void initStatusView() {
        mStatusView = new View(mContext);
        addView(mStatusView);
    }

    private void initTitleText() {
        mTitle = new TextView(mContext);
        addView(mTitle);
    }

    private void initLeftText() {
        mLeftText = new TextView(mContext);
        addView(mLeftText);

        mLeftText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleOnClickListener != null) {
                    titleOnClickListener.onLeftClick();
                } else {
                    Activity activity = (Activity) mContext;
                    activity.finish();
                }
            }
        });
    }

    private void initLeftImage() {
        mLeftImg = new ImageView(mContext);
        addView(mLeftImg);

        mLeftImg.setScaleType(ImageView.ScaleType.CENTER);
        mLeftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleOnClickListener != null) {
                    titleOnClickListener.onLeftClick();
                } else {
                    Activity activity = (Activity) mContext;
                    activity.finish();
                }
            }
        });
    }

    private void initRightImage() {
        mRightImg = new ImageView(mContext);
        addView(mRightImg);

        mRightImg.setScaleType(ImageView.ScaleType.CENTER);
        mRightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleOnClickListener != null) {
                    titleOnClickListener.onRightClick();
                }
            }
        });
    }

    private void initRightText() {
        mRightText = new TextView(mContext);
        addView(mRightText);

        mRightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleOnClickListener != null) {
                    titleOnClickListener.onRightClick();
                }
            }
        });
    }

    /**
     * 测量文字
     *
     * @param spSize    字体尺寸
     * @param direction 方向  0-left  1-center 2-right
     * @param text      文字内容
     * @author ysc
     * @time 2018/10/18 17:08
     */
    private void measureText(String text, float spSize, int direction) {

        //测量文字宽度
        params.mTextSizePaint.setTextSize(spSize == 0 ? SizeUtils.sp2px(mContext, 14) : spSize);
        Paint.FontMetrics fontMetrics = params.mTextSizePaint.getFontMetrics();
        switch (direction) {
            case 0:
                params.mLeftStrWidth = params.mTextSizePaint.measureText(text);
                params.mLeftStrHeight = fontMetrics.bottom - fontMetrics.top;
                break;
            case 1:
                params.mStrWidth = params.mTextSizePaint.measureText(text);
                params.mStrHeight = fontMetrics.bottom - fontMetrics.top;
                break;
            case 2:
                params.mRightStrWidth = params.mTextSizePaint.measureText(text);
                params.mRightStrHeight = fontMetrics.bottom - fontMetrics.top;
                break;
        }
    }


    /**
     * 设置标题的点击监听
     *
     * @param titleOnClickListener titleBar监听器
     */
    public void setOnTitleClickListener(TitleOnClickListener titleOnClickListener) {
        this.titleOnClickListener = titleOnClickListener;
    }

    class LayoutParams {
        /**
         * 标题栏的背景颜色
         */
        int mBackgroundColor;
        /**
         * 标题栏的显示的标题文字
         */
        String mText;
        /**
         * 标题栏的显示的标题文字颜色
         */
        int mTextColor;
        /**
         * 标题栏的显示的标题文字大小
         */
        int mTextSize;


        /**
         * 返回按钮的资源图片
         */
        int mLeftImageResId;
        /**
         * 返回按钮上显示的文字
         */
        String mLeftText;
        /**
         * 返回按钮上显示的文字颜色
         */
        int mLeftTextColor;
        /**
         * 返回按钮上显示的文字大小
         */
        int mLeftTextSize;
        /**
         * 是否显示返回按钮
         */
        boolean mShowDisplayBack = true;


        /**
         * 右边保存按钮的资源图片
         */
        int mRightImageResId;
        /**
         * 右边保存按钮的文字
         */
        String mRightText;
        /**
         * 右边保存按钮的文字颜色
         */
        int mRightTextColor;
        /**
         * 右边保存按钮的文字大小
         */
        int mRightTextSize;
        /**
         * 是否显示右边保存按钮
         */
        boolean mShowDisplaySave;

        /**
         * 是否设置沉浸式状态栏
         */
        boolean mShowDisplayStatus = true;

        /**
         * title字体宽度
         */
        float mStrWidth;

        /**
         * title字体高度
         */
        float mStrHeight;

        /**
         * 组件高度
         */
        int mHeight;

        /**
         * 组件宽度
         */
        int mWidth;

        /**
         * 状态栏高度
         */
        int mViewStatusHeight;

        /**
         * 左边字体宽度
         */
        float mLeftStrWidth;

        /**
         * 左边字体高度
         */
        float mLeftStrHeight;

        /**
         * 右边字体宽度
         */
        float mRightStrWidth;

        /**
         * 右边字体高度
         */
        float mRightStrHeight;

        //###############################################################
        //测量文字高度的画笔
        Paint mTextSizePaint;
    }

    /**
     * 监听标题点击接口
     */
    public interface TitleOnClickListener {
        /**
         * 返回按钮的点击事件
         */
        void onLeftClick();

        /**
         * 保存按钮的点击事件
         */
        void onRightClick();

    }
}

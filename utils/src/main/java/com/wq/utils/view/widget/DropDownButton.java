package com.wq.utils.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wq.utils.R;
import com.wq.utils.util.SizeUtils;

/**
 * 项目名称：    com.wq.utils.view.widget
 * 类描述        带三角的下拉组件（带动画）
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/23
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/23
 * 修改备注：
 */
public class DropDownButton extends RelativeLayout {

    private Context context;

    private static final int DROP_CENTER = 0,//居中
            DROP_LEFT = 1,//居左
            DROP_RIGHT = 2;//居右

    private static final int DROP_DEFAULT_TEXTCOLOR = Color.parseColor("#333333");

    /**
     * 子组件位置
     * {@link android.view.Gravity}
     */
    private int mGravity;

    /**
     * 文字
     */
    private String mText;

    /** 文字颜色 */
    private int mTextColor;

    /**
     * 文字组件
     */
    private TextView mTextView;

    /**
     * 图片组件
     */
    private ImageView mIv;

    /**
     * 测量文字宽高画笔
     */
    private Paint mTextSizePaint;

    /** 文字宽度 */
    private float mTextWidth;

    /** 文字高度 */
    private float mTextHeight;

    /**
     * 组件宽度
     */
    private int mHeight;

    /**
     * 组件高度
     */
    private int mWidth;

    /** 图片 */
    private Drawable mImage;

    /**
     * 构造方法
     *
     * @param context
     */
    public DropDownButton(Context context) {
        this(context, null);
    }

    /**
     * 构造方法
     *
     * @param context
     * @param attrs
     */
    public DropDownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        obtainAttributes(attrs);
        setChildView();
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return super.getSuggestedMinimumWidth();
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return super.getSuggestedMinimumHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = measure(widthMeasureSpec,mTextWidth);
        mHeight = measure(heightMeasureSpec,mTextHeight);
        setMeasuredDimension(mWidth,mHeight);
    }

    private int measure(int measureSpec,float textSize) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {

        }
        return result;
    }

    private void obtainAttributes(AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DropDownButton);
        for (int i = 0; i < array.getIndexCount(); i++) {
            int attr = array.getIndex(i);
            if (attr == R.styleable.DropDownButton_gravity_mode) {
                mGravity = array.getInt(attr, DROP_CENTER);
            } else if (attr == R.styleable.DropDownButton_drop_text) {
                mText = array.getString(attr);
            } else if (attr == R.styleable.DropDownButton_drop_textColor) {
                mTextColor = array.getColor(attr, DROP_DEFAULT_TEXTCOLOR);
            } else if (attr == R.styleable.DropDownButton_drop_image){
                mImage = array.getDrawable(attr);
            }
        }
        array.recycle();
    }

    private void setChildView() {

        mTextSizePaint = new Paint();

        if (mText != null) {
            setText(mText);
        }

        if (mTextColor != 0) {
            setTextColor(mTextColor);
        }

        if (mImage == null){
            setImage(R.drawable.top_arr);
        } else {
            setImage(mImage);
        }
    }

    /**
     * 设置文字
     *
     * @param text 文字内容
     */
    public void setText(String text) {
        if (mTextView == null) {
            initTextView();
        }

        mTextView.setText(text);

        measureText(text,mTextView.getTextSize());
    }

    /**
     * 设置文字字体颜色
     *
     * @param textColor 文字颜色
     */
    public void setTextColor(int textColor) {
        if (mTextView == null) {
            initTextView();
        }

        mTextView.setTextColor(textColor);

        measureText(mTextView.getText().toString(),textColor);
    }

    /**
     * 设置图片
     * @param res
     */
    public void setImage(@DrawableRes int res){
        if (mIv == null){
            initImageView();
        }

        mIv.setImageResource(res);
    }

    /**
     * 设置图片
     * @param drawable
     */
    public void setImage(Drawable drawable){
        if (mIv == null){
            initImageView();
        }

        mIv.setImageDrawable(drawable);
    }

    private void initTextView() {
        mTextView = new TextView(context);
        addView(mTextView);
    }

    private void initImageView(){
        mIv = new ImageView(context);
        addView(mIv);
    }

    /**
     * 测量文字
     *
     * @param spSize 字体尺寸
     * @param text   文字内容
     * @author ysc
     * @time 2018/10/18 17:08
     */
    private void measureText(String text, float spSize) {

        //测量文字宽度
        mTextSizePaint.setTextSize(spSize == 0 ? SizeUtils.sp2px(context, 14) : spSize);
        Paint.FontMetrics fontMetrics = mTextSizePaint.getFontMetrics();
        mTextWidth = mTextSizePaint.measureText(text);
        mTextHeight = fontMetrics.bottom - fontMetrics.top;
    }
}

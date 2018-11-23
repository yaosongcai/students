package com.wq.utils.view.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.wq.utils.R;


/**
 * 项目名称：    com.wq.smartcampus.approval.ui.widget
 * 类描述
 * <pre>
 *     小红点组件，用于显示未读消息
 *     用于需要圆角矩形框背景的TextView的情况,减少直接使用TextView时引入的shape资源文件
 *     @attr-msgView ref R.styleable#MsgView_mv_backgroundColor    圆角矩形背景色
 *     @attr-msgView ref R.styleable#MsgView_mv_cornerRadius       圆角弧度,单位dp
 *     @attr-msgView ref R.styleable#MsgView_mv_strokeWidth        圆角弧度,单位dp
 *     @attr-msgView ref R.styleable#MsgView_mv_strokeColor        圆角边框颜色
 *     @attr-msgView ref R.styleable#MsgView_mv_isRadiusHalfHeight 圆角弧度是高度一半
 *     @attr-msgView ref R.styleable#MsgView_mv_isWidthHeightEqual 圆角矩形宽高相等,取较宽高中大值
 * </pre>
 *
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/9/19
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/9/19
 * 修改备注：
 */
@SuppressLint("AppCompatCustomView")
public class MsgView extends TextView {
    private Context context;
    private GradientDrawable gd_background = new GradientDrawable();
    private int backgroundColor;
    private int cornerRadius;
    private int strokeWidth;
    private int strokeColor;
    private boolean isRadiusHalfHeight;
    private boolean isWidthHeightEqual;

    public MsgView(Context context) {
        this(context, null);
    }

    public MsgView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MsgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        obtainAttributes(context, attrs);
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MsgView);
        backgroundColor = ta.getColor(R.styleable.MsgView_mv_backgroundColor, Color.TRANSPARENT);
        cornerRadius = ta.getDimensionPixelSize(R.styleable.MsgView_mv_cornerRadius, 0);
        strokeWidth = ta.getDimensionPixelSize(R.styleable.MsgView_mv_strokeWidth, 0);
        strokeColor = ta.getColor(R.styleable.MsgView_mv_strokeColor, Color.TRANSPARENT);
        isRadiusHalfHeight = ta.getBoolean(R.styleable.MsgView_mv_isRadiusHalfHeight, false);
        isWidthHeightEqual = ta.getBoolean(R.styleable.MsgView_mv_isWidthHeightEqual, false);

        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isWidthHeightEqual() && getWidth() > 0 && getHeight() > 0) {
            int max = Math.max(getWidth(), getHeight());
            int measureSpec = MeasureSpec.makeMeasureSpec(max, MeasureSpec.EXACTLY);
            super.onMeasure(measureSpec, measureSpec);
            return;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (isRadiusHalfHeight()) {
            setCornerRadius(getHeight() / 2);
        } else {
            setBgSelector();
        }
    }

    /**
     * 设置背景颜色
     * @param backgroundColor   背景颜色
     */
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        setBgSelector();
    }

    /**
     * 设置圆角弧度
     * @param cornerRadius  弧度
     */
    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = dp2px(cornerRadius);
        setBgSelector();
    }

    /**
     * 设置线条宽度
     * @param strokeWidth   宽度
     */
    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = dp2px(strokeWidth);
        setBgSelector();
    }

    /**
     * 设置线条颜色
     * @param strokeColor   颜色
     */
    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        setBgSelector();
    }

    /**
     * 设置圆角弧度为高度的一半
     * @param isRadiusHalfHeight    是否是高度的遗言
     */
    public void setIsRadiusHalfHeight(boolean isRadiusHalfHeight) {
        this.isRadiusHalfHeight = isRadiusHalfHeight;
        setBgSelector();
    }

    /**
     * 圆角矩形宽高相等,取较宽高中大值
     * @param isWidthHeightEqual    宽高是否相等
     */
    public void setIsWidthHeightEqual(boolean isWidthHeightEqual) {
        this.isWidthHeightEqual = isWidthHeightEqual;
        setBgSelector();
    }

    /**
     * 返回背景颜色
     * @return  背景颜色
     */
    public int getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * 返回圆角弧度
     * @return  弧度
     */
    public int getCornerRadius() {
        return cornerRadius;
    }

    /**
     * 返回线条宽度
     * @return  宽度
     */
    public int getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * 返回线条颜色
     * @return  颜色
     */
    public int getStrokeColor() {
        return strokeColor;
    }

    /**
     * 返回圆角弧度是否是高度的一半
     * @return  true  false
     */
    public boolean isRadiusHalfHeight() {
        return isRadiusHalfHeight;
    }

    /**
     * 返回宽高是否相等
     * @return  true  false
     */
    public boolean isWidthHeightEqual() {
        return isWidthHeightEqual;
    }

    protected int dp2px(float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    protected int sp2px(float sp) {
        final float scale = this.context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

    private void setDrawable(GradientDrawable gd, int color, int strokeColor) {
        gd.setColor(color);
        gd.setCornerRadius(cornerRadius);
        gd.setStroke(strokeWidth, strokeColor);
    }

    public void setBgSelector() {
        StateListDrawable bg = new StateListDrawable();

        setDrawable(gd_background, backgroundColor, strokeColor);
        bg.addState(new int[]{-android.R.attr.state_pressed}, gd_background);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {//16
            setBackground(bg);
        } else {
            //noinspection deprecation
            setBackgroundDrawable(bg);
        }
    }
}

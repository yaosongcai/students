package com.wq.utils.view.widget.circleCheckBox;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

/**
 * 项目名称：    com.wq.utils.view.widget.circleCheckBox
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/25
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/25
 * 修改备注：
 */
public abstract class MaterialCheckBox extends View implements Checkable {
    private boolean mChecked;
    private final static int DEFAULT_SIZE = 40;

    public MaterialCheckBox(Context context) {
        super(context, null);
    }

    public MaterialCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setChecked(boolean checked) {
        mChecked = checked;
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = dp2px(DEFAULT_SIZE);
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = dp2px(DEFAULT_SIZE);
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    public int dp2px(float value) {
        final float scale = getContext().getResources().getDisplayMetrics().densityDpi;
        return (int) (value * (scale / 160) + 0.5f);
    }

}

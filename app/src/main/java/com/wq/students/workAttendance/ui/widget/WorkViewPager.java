package com.wq.students.workAttendance.ui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * 项目名称：    com.wq.students.workAttendance.ui.widget
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/25
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/25
 * 修改备注：
 */
public class WorkViewPager extends ViewPager {
    public WorkViewPager(Context context) {
        super(context);
    }

    public WorkViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height) height = h;
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

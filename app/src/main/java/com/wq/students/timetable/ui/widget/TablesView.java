package com.wq.students.timetable.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.List;

/**
 * 项目名称：    com.wq.students.timetable.ui.widget
 * 类描述        课表组件
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/15
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/15
 * 修改备注：
 */
public class TablesView extends TablesAbsView {

    //数据
    private List<Colum> colum;

    public TablesView(Context context) {
        this(context, null);
    }

    public TablesView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TablesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
//        setMeasuredDimension(params.width,params.height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (colum == null || colum.size() <= 0) {
            return;
        }

        onDraw(canvas,colum);
    }

    public void setData(List<Colum> list) {
        this.colum = list;
        measures(colum);
        invalidate();
    }

}

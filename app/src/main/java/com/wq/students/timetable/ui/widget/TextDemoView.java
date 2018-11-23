package com.wq.students.timetable.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 项目名称：    com.wq.students.timetable.ui.widget
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/15
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/15
 * 修改备注：
 */
public class TextDemoView extends View {

    private Paint textPaint;

    private Paint bgPaint;

    private Rect rect;

    private Paint.FontMetrics fontMetrics;

    public TextDemoView(Context context) {
        this(context,null);
    }

    public TextDemoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextDemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.parseColor("#252525"));
        textPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,14,getContext().getResources().getDisplayMetrics()));

        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(Color.parseColor("#ff5400"));

        rect = new Rect();
        fontMetrics = textPaint.getFontMetrics();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        int width = (int) textPaint.measureText("你好");
        float descent = fontMetrics.descent;
        float ascent = fontMetrics.ascent;
        float leading = fontMetrics .leading;
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;

        rect.top = 0;
        rect.left = 0;
        rect.right = width;
        rect.bottom = (int)(bottom-top);

        float baseLine = (bottom-top)/2 + Math.abs(textPaint.ascent() + textPaint.descent()) / 2;

        canvas.drawRect(rect,bgPaint);
        canvas.drawText("你好",0,baseLine,textPaint);
    }
}

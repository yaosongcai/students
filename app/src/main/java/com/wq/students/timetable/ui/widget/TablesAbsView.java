package com.wq.students.timetable.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.wq.students.R;
import com.wq.utils.util.SizeUtils;

import java.util.List;

/**
 * 项目名称：    com.wq.students.timetable.ui.widget
 * 类描述        课表控件
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/16
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/16
 * 修改备注：
 */
public abstract class TablesAbsView extends View {

    protected LayoutParams params;

    private int mMeasureWidth;

    private int mmeasureHeight;

    //顶部tab背景默认颜色
    private static final int DEFAULT_TOPBACKGROUND = Color.parseColor("#f3f3f3");

    //线条默认颜色
    private static final int DEFAULT_LINECOLOR = Color.parseColor("#333333"),//f4f3f3
            DEFAULT_LINEHEIGHT = 1;//线条默认高度

    private static final float DEFAULT_PARENT_TEXTSIZE = 14,//默认文本字体尺寸
            DEFAULT_CHILD_TEXTSIZE = 9;//默认子文本字体尺寸

    private static final int DEFAULT_MARGIN_TOP = SizeUtils.dp2px(10),//边框默认上边距
            DEFAULT_MARGIN_LEFT = SizeUtils.dp2px(10),//边框默认左边距
            DEFAULT_MARGIN_RIGHT = SizeUtils.dp2px(10),//边框默认右边距
            DEFAULT_MARGIN_BOTTOM = SizeUtils.dp2px(10);//边框默认下边距

    public TablesAbsView(Context context) {
        this(context, null);
    }

    public TablesAbsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TablesAbsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        params = new LayoutParams();

        obtainAttributes(context, attrs);

        init();
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.TablesView);
        for (int i = 0; i < arr.getIndexCount(); i++) {
            int attr = arr.getIndex(i);
            switch (attr) {
                case R.styleable.TablesView_tabTopBackground:
                    params.topBackground = arr.getColor(attr, DEFAULT_TOPBACKGROUND);
                    break;
                case R.styleable.TablesView_tabLineColor:
                    params.lineColor = arr.getColor(attr, DEFAULT_LINECOLOR);
                    break;
                case R.styleable.TablesView_tabLineHeight:
                    params.lineHeight = arr.getDimensionPixelSize(attr, DEFAULT_LINEHEIGHT);
                    break;
                case R.styleable.TablesView_tabmarginLeft:
                    params.marginLeft = arr.getDimensionPixelSize(attr, DEFAULT_MARGIN_LEFT);
                    break;
                case R.styleable.TablesView_tabmarginTop:
                    params.marginTop = arr.getDimensionPixelSize(attr, DEFAULT_MARGIN_TOP);
                    break;
                case R.styleable.TablesView_tabmarginRight:
                    params.marginRight = arr.getDimensionPixelSize(attr, DEFAULT_MARGIN_RIGHT);
                    break;
                case R.styleable.TablesView_tabmarginBottom:
                    params.marginBottom = arr.getDimensionPixelSize(attr, DEFAULT_MARGIN_BOTTOM);
                    break;
                case R.styleable.TablesView_tabParentTextSize:
                    params.parent_textSize = arr.getDimensionPixelSize(attr, (int) DEFAULT_PARENT_TEXTSIZE);
                    break;
                case R.styleable.TablesView_tabChildTextSize:
                    params.child_textSize = arr.getDimensionPixelSize(attr, (int) DEFAULT_CHILD_TEXTSIZE);
                    break;
            }
        }
        arr.recycle();
    }

    private void init() {
        params.columBgPaint = new Paint();
        params.columBgPaint.setColor(params.topBackground);
        params.columBgPaint.setAntiAlias(true);
        params.textPaint = new Paint();
        params.textPaint.setAntiAlias(true);
        params.textPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_PARENT_TEXTSIZE, getContext().getResources().getDisplayMetrics()));

        params.linePaint = new Paint();
        params.linePaint.setAntiAlias(true);
        params.linePaint.setColor(params.lineColor);
        params.linePaint.setStrokeWidth(params.lineHeight);
        params.linePaint.setStyle(Paint.Style.FILL);

        params.topRect = new Rect();
        params.fontMetrics = params.textPaint.getFontMetrics();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    protected void onDraw(Canvas canvas, List<Colum> colum) {
        for (int i = 0; i < colum.size(); i++) {
            if (colum.get(i).isTopTable) {
                drawTopTable(canvas, colum.get(i));
            } else if (colum.get(i).isMerge) {
                drawMeger(canvas,colum.get(i));
            } else {
                drawContent(canvas, colum.get(i));
            }
        }
    }

    /**
     * 绘制顶部背景加内容
     *
     * @author ysc
     * @time 2018/11/15 11:28
     */
    private void drawTopTable(Canvas canvas, Colum colum) {
        params.topRect.left = 0;
        params.topRect.top = 0;
        params.topRect.right = params.width;
        params.topRect.bottom = colum.height;

        canvas.drawRect(params.topRect, params.columBgPaint);
        canvas.drawLine(0, 0, params.width, 0, params.linePaint);
        canvas.drawLine(0, colum.height, params.width, colum.height, params.linePaint);
        canvas.drawText(colum.leftTable.content, (float) (params.leftTabWidth / 2) - (colum.leftTable.StrWidth / 2), colum.leftTable.baseLine, params.textPaint);
        canvas.drawLine(params.leftTabWidth, 0, params.leftTabWidth, colum.height, params.linePaint);

        for (int i = 0; i < colum.conotent.size(); i++) {
            ChildBean c = colum.conotent.get(i);
            for (int j = 0; j < c.values.size(); j++) {
                float x = params.leftTabWidth + (params.contentWidth * (j)) + (params.contentWidth / 2) - (c.values.get(j).StrWidth / 2);
                canvas.drawText(c.values.get(j).content, x, c.values.get(j).baseLine, params.textPaint);

                if (i != c.values.size() - 1) {

                    canvas.drawLine(params.leftTabWidth + (params.contentWidth * (j + 1)), 0, params.leftTabWidth + (params.contentWidth * (j + 1)), colum.height, params.linePaint);
                }
            }
        }

    }

    /**
     * 绘制内容体
     *
     * @author ysc
     * @time 2018/11/15 17:50
     */
    private void drawContent(Canvas canvas, Colum c) {
        canvas.drawText(c.leftTable.content, (float) (params.leftTabWidth / 2) - (c.leftTable.StrWidth / 2), c.leftTable.baseLine, params.textPaint);
        canvas.drawLine(0, c.currentHeight + c.columHeight, params.width, c.currentHeight + c.columHeight, params.linePaint);
        canvas.drawLine(params.leftTabWidth, c.currentHeight, params.leftTabWidth, c.currentHeight + c.columHeight, params.linePaint);

        for (int i = 0; i < c.conotent.size(); i++) {
            ChildBean bean = c.conotent.get(i);

            if (bean.isMerge){
                float x = (params.width-params.leftTabWidth)/2 - (bean.StrWidth/2) + params.leftTabWidth;
                canvas.drawText(bean.content,x,bean.baseLine,params.textPaint);
                canvas.drawLine(params.width,bean.currentHeight,params.width,bean.currentHeight + bean.height,params.linePaint);
                if (i != c.conotent.size()-1){
                    canvas.drawLine(params.leftTabWidth,bean.currentHeight + bean.height,params.width,bean.currentHeight + bean.height,params.linePaint);
                }
                continue;
            }

            for (int j = 0; j < bean.values.size(); j++) {
                float x = params.leftTabWidth + (params.contentWidth * (j)) + (params.contentWidth / 2) - (bean.values.get(j).StrWidth / 2);
                canvas.drawText(bean.values.get(j).content, x, bean.values.get(j).baseLine, params.textPaint);

                if (i != bean.values.size() - 1) {

                    canvas.drawLine(params.leftTabWidth + (params.contentWidth * (j + 1)), bean.currentHeight, params.leftTabWidth + (params.contentWidth * (j + 1)), bean.currentHeight + c.height, params.linePaint);
                }
            }
            if (i != c.conotent.size()-1){
                canvas.drawLine(params.leftTabWidth,bean.currentHeight + bean.height,params.width,bean.currentHeight + bean.height,params.linePaint);
            }
        }
    }

    /**
     * 绘制合并单元格数据
     * @param canvas
     * @param colum
     * @author ysc
     * @time 2018/11/19 10:30
     */
    private void drawMeger(Canvas canvas,Colum colum){
        canvas.drawLine(0,colum.currentHeight + colum.columHeight,params.width,colum.currentHeight + colum.columHeight,params.linePaint);
        canvas.drawLine(params.width,colum.currentHeight,params.width,colum.currentHeight + colum.columHeight,params.linePaint);
        canvas.drawText(colum.mergeContent.content,(params.width)/2 - colum.mergeContent.StrWidth/2,colum.mergeContent.baseLine,params.textPaint);
    }

    /**
     * 测量
     *
     * @author ysc
     * @time 2018/11/16 10:43
     */
    protected void measures(List<Colum> list) {
        for (int i = 0; i < list.size(); i++) {
            measureLeftTable(list.get(i));
        }
    }

    /**
     * 测量左边的tab
     *
     * @author ysc
     * @time 2018/11/16 10:26
     */
    private void measureLeftTable(Colum colum) {
        colum.currentHeight = params.height;

        if (colum.isMerge){
            params.height += (int) (params.fontMetrics.bottom - params.fontMetrics.top + params.marginBottom + params.marginTop);
            colum.columHeight = (int) (params.fontMetrics.bottom - params.fontMetrics.top + params.marginBottom + params.marginTop);
            colum.mergeContent.baseLine = params.height - ((params.fontMetrics.bottom - params.fontMetrics.top + params.marginBottom + params.marginTop) / 2) + (Math.abs(params.textPaint.ascent() + params.textPaint.descent()) / 2);
            colum.mergeContent.StrWidth = (int) params.textPaint.measureText(colum.mergeContent.content);
            return;
        }

        colum.leftTable.StrWidth = (int) params.textPaint.measureText(colum.leftTable.content);
        params.leftTabWidth = Math.max(colum.leftTable.StrWidth + params.marginLeft + params.marginRight, params.leftTabWidth) + params.marginRight + params.marginLeft;

        for (int i = 0; i < colum.conotent.size(); i++) {

            mmeasureHeight = params.height;

            measureItem(colum.conotent.get(i));

            params.height = Math.max(mmeasureHeight, params.height);
            colum.height = colum.conotent.get(i).height;
        }

        colum.columHeight += params.height - colum.currentHeight;
        colum.leftTable.baseLine = params.height - ((colum.columHeight) / 2) + (Math.abs(params.textPaint.ascent() + params.textPaint.descent()) / 2);
    }

    /**
     * 测量一个tab的每一行
     *
     * @author ysc
     * @time 2018/11/16 10:26
     */
    private void measureItem(ChildBean bean) {
        mMeasureWidth = params.leftTabWidth;
        bean.currentHeight = params.height;
        bean.height = (int) (params.fontMetrics.bottom - params.fontMetrics.top + params.marginBottom + params.marginTop);
        mmeasureHeight += bean.height;

        if (bean.isMerge){
            bean.StrWidth = (int) params.textPaint.measureText(bean.content);
            bean.baseLine =params.height + ((bean.height) / 2) + (Math.abs(params.textPaint.ascent() + params.textPaint.descent()) / 2);
            return;
        }

        for (int i = 0; i < bean.values.size(); i++) {
            measureGrid(bean.values.get(i));
        }

        params.width = Math.max(params.width, params.leftTabWidth + (params.contentWidth * bean.values.size()));//mMeasureWidth

    }

    /**
     * 测量每个方格
     *
     * @author ysc
     * @time 2018/11/16 10:26
     */
    private void measureGrid(Values values) {
        values.StrWidth = (int) params.textPaint.measureText(values.content);
        values.baseLine = params.height + ((params.marginTop + params.marginBottom + params.fontMetrics.bottom - params.fontMetrics.top) / 2) + (Math.abs(params.textPaint.ascent() + params.textPaint.descent()) / 2);
        params.contentWidth = Math.max(values.StrWidth + params.marginRight + params.marginLeft, params.contentWidth);
        mMeasureWidth += params.contentWidth;

    }

    class LayoutParams {

        //顶部tab背景颜色
        int topBackground = DEFAULT_TOPBACKGROUND;

        //分割线颜色
        int lineColor = DEFAULT_LINECOLOR;

        //线条高度
        int lineHeight = DEFAULT_LINEHEIGHT;

        //列背景画笔
        Paint columBgPaint;

        //文字画笔
        Paint textPaint;

        //分割线画笔
        Paint linePaint;

        //顶部背景矩形
        Rect topRect;

        //文字测量器
        Paint.FontMetrics fontMetrics;

        //宽度
        int width;

        int height;

        //边框上边距
        int marginTop = DEFAULT_MARGIN_TOP;

        //边框左边距
        int marginLeft = DEFAULT_MARGIN_LEFT;

        //边框右边距
        int marginRight = DEFAULT_MARGIN_RIGHT;

        //边框下边距
        int marginBottom = DEFAULT_MARGIN_BOTTOM;

        //左边tab宽度
        int leftTabWidth;

        //每个选项宽度
        int contentWidth;

        //默认文本字体尺寸
        float parent_textSize;

        //默认子文本字体尺寸
        float child_textSize;
    }
}

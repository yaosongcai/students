package com.wq.utils.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.wq.utils.R;
import com.wq.utils.util.ScreenUtils;
import com.wq.utils.util.SizeUtils;
import com.wq.utils.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：    com.wq.utils.view.widget
 * 类描述        成绩展示页面视图
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/24
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/24
 * 修改备注：
 */
public class AchieveView extends View {

    private final static int DEFAULT_TITLE_BACKGROUDN = Color.parseColor("#f3f3f3"),
                                DEFAULT_TEXTCOLOR = Color.parseColor("#3d444e"),
                                DEFAULT_MSGCOLOR = Color.parseColor("#ff6161");

    private Context mContext;

    private LayoutParams params;

    //组件宽度
    private int mWidth;

    //组件高度
    private int mHeight;

    //文字画笔
    private Paint mTextPaint;

    //消息画笔
    private Paint mMsgPaint;

    //顶部标题背景画笔
    private Paint mTitleBackgroundPaint;

    //标题数据源
    private List<String> titleList;

    private List<Column> columns;

    private OnDrawListener onDrawListener;

    private List<ChildMarginParams> marginParamses = new ArrayList<>();

    public AchieveView(Context context) {
        this(context,null);
    }

    public AchieveView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;

        params = new LayoutParams();

        setWillNotDraw(false);


        obtainAttributes(attrs);

        init();

    }

    private void obtainAttributes(AttributeSet attrs){
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.AchieveView);
        for (int i = 0;i<array.getIndexCount();i++){
            int attr = array.getIndex(i);
            if (attr == R.styleable.AchieveView_achieve_titleBackground) {
                params.titleBackground = array.getColor(attr,DEFAULT_TITLE_BACKGROUDN);
            } else if (attr == R.styleable.AchieveView_achieve_textColor){
                params.mTextColor = array.getColor(attr,DEFAULT_TEXTCOLOR);
            } else if (attr == R.styleable.AchieveView_achieve_textSize){
                params.mTextSize = array.getDimension(attr,mContext.getResources().getDimension(R.dimen.achivev_textsize));
            } else if (attr == R.styleable.AchieveView_achieve_msgColor){
                params.mMsgColor = array.getColor(attr,DEFAULT_MSGCOLOR);
            } else if (attr == R.styleable.AchieveView_achieve_msgSize){
                params.mMsgSize = array.getDimension(attr,mContext.getResources().getDimension(R.dimen.achivev_msgsize));
            }
        }
        array.recycle();
    }

    private void init(){
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(params.mTextColor == 0?DEFAULT_TEXTCOLOR:params.mTextColor);
        mTextPaint.setTextSize(params.mTextSize == 0?mContext.getResources().getDimension(R.dimen.achivev_textsize):params.mTextSize);

        mTitleBackgroundPaint = new Paint();
        mTitleBackgroundPaint.setAntiAlias(true);
        mTitleBackgroundPaint.setColor(params.titleBackground == 0?DEFAULT_TITLE_BACKGROUDN:params.titleBackground);

        mMsgPaint = new Paint();
        mMsgPaint.setAntiAlias(true);
        mMsgPaint.setColor(params.mMsgColor == 0?DEFAULT_MSGCOLOR:params.mMsgColor);
        mMsgPaint.setTextSize(params.mMsgSize == 0?mContext.getResources().getDimension(R.dimen.achivev_msgsize):params.mMsgSize);
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return super.getSuggestedMinimumHeight();
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return super.getSuggestedMinimumWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = ScreenUtils.getScreenWidth();
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRGB(255,255,255);
        if (titleList ==null || titleList.size() <= 0){
            return;
        }
        //绘制顶部背景
        Rect rect = new Rect(0,0,mWidth,marginParamses.get(0).mHeight);
        canvas.drawRect(rect,mTitleBackgroundPaint);

        //绘制顶部标题
        drawTitle(canvas);

        //绘制数据
        drawData(canvas);
    }

    /**
     * 绘制顶部标题
     * @param canvas
     */
    private void drawTitle(Canvas canvas){

        if (titleList == null || titleList.size() <= 0){
            return;
        }

        int width = 0;
        for (int i = 0; i < titleList.size();i++){
            canvas.drawText(titleList.get(i),width + marginParamses.get(i).left,marginParamses.get(i).descent,mTextPaint);
            width += marginParamses.get(i).left + marginParamses.get(i).textWidth + marginParamses.get(i).right;
        }
    }

    /**
     *  绘制数据
     * @param canvas
     */
    private void drawData(Canvas canvas){
        if (columns == null || columns.size() <= 0){
            return;
        }
        int height = marginParamses.get(0).mHeight;
        for (int i = 0; i < columns.size();i++){
            int width = 0;
            height += columns.get(i).paramses.get(0).mHeight;
            for (int j = 0; j < columns.get(i).values.size();j++){
                reset();
                if (onDrawListener!= null){
                    onDrawListener.onDrawListener(columns.get(i).values.get(j),mTextPaint);
                }
                canvas.drawText(columns.get(i).values.get(j).value,width+((columns.get(i).paramses.get(j).mWidth/2) - (columns.get(i).paramses.get(j).textWidth/2)),height - columns.get(i).paramses.get(j).mHeight + columns.get(i).paramses.get(j).descent,mTextPaint);
                if (!StringUtils.isEmpty(columns.get(i).values.get(j).msg)){
                    canvas.drawText(columns.get(i).values.get(j).msg,width+((columns.get(i).paramses.get(j).mWidth/2) + (columns.get(i).paramses.get(j).textWidth/2)),height -columns.get(i).paramses.get(j).mHeight- columns.get(i).paramses.get(j).ascent,mMsgPaint);
                }
                width += columns.get(i).paramses.get(j).mWidth;
            }
        }
    }

    private void reset(){

        mTextPaint.setColor(params.mTextColor == 0?DEFAULT_TEXTCOLOR:params.mTextColor);
        mTextPaint.setTextSize(params.mTextSize == 0?mContext.getResources().getDimension(R.dimen.achivev_textsize):params.mTextSize);
    }

    /**
     * 设置标题数据源
     * @param titleList     标题数据源
     */
    public void setTitle(List<String> titleList){
        this.titleList = titleList;
        measureMargin();
        invalidate();
    }

    public void setData(List<Column> list){
        this.columns = list;
        measureDataMargin();
        invalidate();
    }

    private void measureMargin(){
        //测量文字宽度
        mTextPaint.setTextSize(params.mTextSize == 0? SizeUtils.sp2px(mContext,14):params.mTextSize);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();

        int width = 0;
        for (int i = 0; i < titleList.size();i++){
            if (marginParamses.size()<=0 || marginParamses.size()-1 < i){
                ChildMarginParams params = new ChildMarginParams();
                params.left = SizeUtils.dp2px(mContext,10);
                params.right = SizeUtils.dp2px(mContext,10);
                params.top = SizeUtils.dp2px(mContext,5);
                params.bottom = SizeUtils.dp2px(mContext,10);
                params.textWidth = mTextPaint.measureText(titleList.get(i));
                params.textHeight = fontMetrics.bottom-fontMetrics.top;
                params.descent = params.top + fontMetrics.descent-fontMetrics.ascent;
                params.mHeight = (int) Math.max(params.textHeight + params.top + params.bottom,params.mHeight);
                params.mWidth = (int) (params.left + params.right + params.textWidth);
                width += mWidth;
                marginParamses.add(params);
            } else {
                marginParamses.get(i).textWidth = mTextPaint.measureText(titleList.get(i));
                marginParamses.get(i).textHeight = fontMetrics.bottom-fontMetrics.top;

                marginParamses.get(i).descent = marginParamses.get(i).top + fontMetrics.descent-fontMetrics.ascent;
                width += marginParamses.get(i).textWidth + marginParamses.get(i).right + marginParamses.get(i).left;
                marginParamses.get(i).mHeight = (int) Math.max(marginParamses.get(i).textHeight + marginParamses.get(i).top + marginParamses.get(i).bottom,marginParamses.get(i).mHeight);
            }
        }

        mHeight += marginParamses.get(0).mHeight;
        mWidth = Math.max(mWidth,width);
    }

    private void measureDataMargin(){
        //测量文字宽度
        mTextPaint.setTextSize(params.mTextSize == 0? SizeUtils.sp2px(mContext,14):params.mTextSize);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();

        for (int i = 0;i< columns.size();i++){
            int width = 0;
            List<ChildMarginParams> paramses = new ArrayList<>();
            for (int j = 0;j < columns.get(i).values.size(); j++){
                ChildMarginParams params = new ChildMarginParams();
                params.left = SizeUtils.dp2px(mContext,10);
                params.right = SizeUtils.dp2px(mContext,10);
                params.top = SizeUtils.dp2px(mContext,5);
                params.bottom = SizeUtils.dp2px(mContext,10);

                params.textWidth = mTextPaint.measureText(columns.get(i).values.get(j).value);
                params.textHeight = fontMetrics.bottom-fontMetrics.top;
                params.mWidth = (int) Math.max(marginParamses.get(j).mWidth,params.left + params.right + params.textWidth);

                params.ascent = (int) (params.top + fontMetrics.ascent) - SizeUtils.dp2px(mContext,3);
                params.descent = params.top + fontMetrics.descent-fontMetrics.ascent;
                width += params.mWidth;
                params.mHeight = (int) Math.max(params.textHeight + params.top + params.bottom,marginParamses.get(0).mHeight);
                paramses.add(params);

                columns.get(i).values.get(j).page = j + 1;
                columns.get(i).values.get(j).row = i + 2;
            }

            columns.get(i).paramses = paramses;
            mHeight += marginParamses.get(0).mHeight;
            mWidth = Math.max(mWidth,width);
        }
    }

    class LayoutParams{

        //字体颜色
        int mTextColor;

        //顶部标题背景
        int titleBackground;

        //字体尺寸
        float mTextSize;

        //小红点颜色
        int mMsgColor;

        //小红点大小
        float mMsgSize;

    }

    class ChildMarginParams{

        //左边距
        float left;

        //上边距
        float top;

        //右边距
        float right;

        //下边距
        float bottom;

        //字体宽度
        float textWidth;

        //字体高度
        float textHeight;

        //当前可绘制的最底线
        float descent;

        //行高
        int mHeight;

        //行宽
        int mWidth;

        //字体顶部基准线
        int ascent;

    }

    public static class Column{

        //值
        public List<Values> values;

        List<ChildMarginParams> paramses;

    }

    public static class Values{

        public String value;//值

        public String msg;//消息

        public int row;//行

        public int page;//列
    }

    /**
     *
     * 钩子 用于绘制特定值时改变颜色与值
     * @param onDrawListener
     * @author ysc
     * @time 2018/10/24 17:27
     */
    public void setOnDrawListener(OnDrawListener onDrawListener) {
        this.onDrawListener = onDrawListener;
    }

    /**
     *
     * 钩子 用于绘制特定值时改变颜色与值
     * @author ysc
     * @time 2018/10/24 17:24
     */
    public interface OnDrawListener{
        /**
         * 钩子 用于绘制特定值时改变颜色与值
         * @param values
         * @param paint
         */
        void onDrawListener(Values values,Paint paint);
    }
}

package com.wq.students.reward.ui.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.wq.utils.util.BarUtils;

/**
 * Created by  HONGDA on 2018/6/14.
 */
public class ObservableScrollView extends NestedScrollView {
    private ScrollViewListener scrollViewListener = null;
    private View mHeaderView;
    private View mBottomView;
    float mHeaderHeight;
    float mBottomHeight;
    int mLastY;
    int state;//1-下移  2-上移
    int[] location = new int[2];

    float mScaleRatio = 0.4f; //    滑动放大系数，系数越大，滑动时放大程度越大
    float mScaleTimes = 2.0f;//     最大的放大倍数
    float mReplyRatio = 0.5f; //    回弹时间系数，系数越小，回弹越快

    boolean mIsPulling = false;//是否正在滑动

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        //设置不可过度滚动
//        setOverScrollMode(OVER_SCROLL_NEVER);
        View child = getChildAt(0);
        if (child != null && child instanceof ViewGroup) {
            mHeaderView = ((ViewGroup) child).getChildAt(0);   //获取默认第一个子View
            mBottomView = ((ViewGroup)mHeaderView).getChildAt(3);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastY = (int) ev.getY();
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mHeaderView == null)
            return super.onTouchEvent(ev);

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                state = mLastY-ev.getY() <= 0?1:2;
                break;
            case MotionEvent.ACTION_UP:
                mBottomView.getLocationOnScreen(location);
                if (state == 1){
                    if (location[1] >= mHeaderHeight/2){
                        fullScroll(FOCUS_UP);
                    }else {
                        scrollTo(0, (int) mHeaderHeight);
                    }
                } else if (state == 2){
                    if (location[1] >= mHeaderHeight/2){
                        scrollTo(0,(int) mHeaderHeight);
                    } else {
                        fullScroll(FOCUS_UP);
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mHeaderView == null)
            return super.onTouchEvent(ev);

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (!mIsPulling) {//当滑到顶部时 getScrollY为0开始记录手指按下的位置
                    if (getScrollY() != 0) break;
                    mLastY = (int) ev.getY();
                }

                if (ev.getY() - mLastY < 0) {
                    return super.onTouchEvent(ev);   //之前逻辑如果上划知识调用系统的上划
                }

                mIsPulling = true;
                setZoom((int) ((ev.getY() - mLastY) * mScaleRatio));
                return true;
            case MotionEvent.ACTION_UP:
                mIsPulling = false;
                if (getScrollY() == 0) {
                    replyView();
                }
                soptionView();
                break;
        }
        return super.onTouchEvent(ev);
    }*/

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    /**
     * 控制view的拉伸
     */
    private void setZoom(float s) {
        float scaleTimes = (float) ((mHeaderHeight + s) / (mHeaderHeight * 1.0));
        if (scaleTimes > mScaleTimes) return;// 如超过最大放大倍数，直接返回
        ViewGroup.LayoutParams layoutParams = mHeaderView.getLayoutParams();
        layoutParams.height = (int) (mHeaderHeight * ((mHeaderHeight + s) / mHeaderHeight));
        mHeaderView.setLayoutParams(layoutParams);

    }

    /**
     * 回弹
     */
    private void soptionView() {
        final int absY = getScrollY();
        //在 距离顶部0.7 或者大于顶部0.3距离后划回
        if ((mHeaderHeight > absY && absY > (mHeaderHeight * 0.7)) || (absY > mHeaderHeight && absY < (mHeaderHeight * 1.3))) {
            scrollTo(0, (int) mHeaderHeight);
        }
    }


    /**
     * 回弹
     */
    private void replyView() {
        final float distance = mHeaderView.getMeasuredHeight() - mHeaderHeight;
        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(distance, 0.0F).setDuration((long) (distance * mReplyRatio));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setZoom((Float) animation.getAnimatedValue());
            }
        });
        anim.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeaderHeight = mHeaderView.getMeasuredHeight() + BarUtils.getStatusBarHeight();
        mBottomHeight = mHeaderView.getMeasuredHeight();
    }

    public interface ScrollViewListener {
        void onScrollChanged(NestedScrollView scrollView, int x, int y, int oldx, int oldy);
    }

}


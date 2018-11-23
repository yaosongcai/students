/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wq.utils.view.widget;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

import com.wq.utils.R;

/**
 * ================================================
 * 因为继承于 {@link PopupWindow} ,所以它本身就是一个 {@link PopupWindow}
 * 因此如果此类里封装的功能并不能满足您的需求(不想过多封装 UI 的东西,这里只提供思想,觉得不满足需求可以自己仿照着封装)
 * 您可以直接调用 {@link PopupWindow} 的 Api 满足需求
 *
 * ================================================
 */
public class CustomPopupWindow extends PopupWindow {
    private View mContentView;
    private View mParentView;
    private View layer;
    private CustomPopupWindowListener mListener;
    private boolean isOutsideTouch;
    private boolean isFocus;
    private Drawable mBackgroundDrawable;
    private int mAnimationStyle;
    private boolean isHWrap;
    private boolean isWWrap;
    private Animation animIn, animOut;

    private CustomPopupWindow(Builder builder) {
        this.mContentView = builder.contentView;
        this.mParentView = builder.parentView;
        this.layer = builder.layer;
        this.mListener = builder.listener;
        this.isOutsideTouch = builder.isOutsideTouch;
        this.isFocus = builder.isFocus;
        this.mBackgroundDrawable = builder.backgroundDrawable;
        this.mAnimationStyle = builder.animationStyle;
        this.animIn = builder.animIn;
        this.animOut = builder.animOut;
        this.isHWrap = builder.isHWrap;
        this.isWWrap = builder.isWWrap;
        initLayout();
    }

    /**
     * 返回构建Builder类
     * @return
     */
    public static Builder builder() {
        return new Builder();
    }

    private void initLayout() {
        if (mListener != null)mListener.initPopupView(mContentView);
        if (animIn == null)animIn = AnimationUtils.loadAnimation(getContentView().getContext(), R.anim.fade_in_anim);
        if (animOut == null)animOut = AnimationUtils.loadAnimation(getContentView().getContext(), R.anim.fade_out_anim);
        setWidth(isWWrap ? LayoutParams.WRAP_CONTENT : LayoutParams.MATCH_PARENT);
        setHeight(isHWrap ? LayoutParams.WRAP_CONTENT : LayoutParams.MATCH_PARENT);
        setFocusable(isFocus);
        setOutsideTouchable(isOutsideTouch);
        setBackgroundDrawable(mBackgroundDrawable);
        if (mAnimationStyle != -1)//如果设置了动画则使用动画
            setAnimationStyle(mAnimationStyle);
        setContentView(mContentView);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {

                if (layer != null){
                    layer.startAnimation(animOut);
                    layer.setVisibility(View.GONE);
                }

                if (mListener != null){
                    mListener.onDismiss();
                }
            }
        });
    }

    /**
     * 获得用于展示popup内容的view
     *
     * @return
     */
    @Override
    public View getContentView() {
        return mContentView;
    }

    /**
     * 用于填充contentView,必须传ContextThemeWrapper(比如activity)不然popupwindow要报错
     * @param context
     * @param layoutId
     * @return
     */
    public static View inflateView(ContextThemeWrapper context, int layoutId) {
        return LayoutInflater.from(context)
                .inflate(layoutId, null);
    }

    /**
     * 默认显示到中间
     */
    public void show() {

        if (layer != null){
            layer.startAnimation(animIn);
            layer.setVisibility(View.VISIBLE);
        }

        if (mParentView == null) {
            showAtLocation(mContentView, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        } else {
            showAtLocation(mParentView, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    }

    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        if (layer != null){
            layer.startAnimation(animIn);
            layer.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 构建类
     */
    public static final class Builder {
        private View contentView;
        private View parentView;
        private View layer;//遮挡层
        private CustomPopupWindowListener listener;
        private boolean isOutsideTouch = true;//默认为true
        private boolean isFocus = true;//默认为true
        private Drawable backgroundDrawable = new ColorDrawable(0x00000000);//默认为透明
        private int animationStyle = -1;
        private boolean isHWrap;
        private boolean isWWrap;
        private Animation animIn, animOut;

        private Builder() {
        }

        /**
         * 构建正文组件
         * @param contentView   正文组件
         * @return
         */
        public Builder contentView(View contentView) {
            this.contentView = contentView;
            return this;
        }

        /**
         * 构建父组件
         * @param parentView    父组件
         * @return
         */
        public Builder parentView(View parentView) {
            this.parentView = parentView;
            return this;
        }

        /**
         * 构建遮挡层
         * @param layer
         * @return
         */
        public Builder layer(View layer){
            this.layer = layer;
            return this;
        }

        /**
         * 组件高度
         * @param isHWrap
         * @return
         */
        public Builder isHWrap(boolean isHWrap) {
            this.isHWrap = isHWrap;
            return this;
        }

        /**
         * 组件宽度
         * @param isWWrap
         * @return
         */
        public Builder isWWrap(boolean isWWrap){
            this.isWWrap = isWWrap;
            return this;
        }


        public Builder customListener(CustomPopupWindowListener listener) {
            this.listener = listener;
            return this;
        }


        public Builder isOutsideTouch(boolean isOutsideTouch) {
            this.isOutsideTouch = isOutsideTouch;
            return this;
        }

        public Builder isFocus(boolean isFocus) {
            this.isFocus = isFocus;
            return this;
        }

        public Builder backgroundDrawable(Drawable backgroundDrawable) {
            this.backgroundDrawable = backgroundDrawable;
            return this;
        }

        public Builder animationStyle(int animationStyle) {
            this.animationStyle = animationStyle;
            return this;
        }

        /**
         * 遮挡层背景变暗动画
         * @param animIn
         * @return
         */
        public Builder animIn(Animation animIn){
            this.animIn = animIn;
            return this;
        }

        /**
         * 遮挡层背景消失动画
         * @param animOut
         * @return
         */
        public Builder animOut(Animation animOut){
            this.animOut = animOut;
            return this;
        }

        public CustomPopupWindow build() {
            if (contentView == null)
                throw new IllegalStateException("需要内容视图  ContentView is required");
            if (listener == null)
                throw new IllegalStateException("需要监听  CustomPopupWindowListener is required");

            return new CustomPopupWindow(this);
        }
    }

    public interface CustomPopupWindowListener {
        /**
         * 视图初始化
         * @param contentView
         */
        public void initPopupView(View contentView);

        public void onDismiss();
    }

}

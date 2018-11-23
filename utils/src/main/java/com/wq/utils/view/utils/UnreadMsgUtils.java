package com.wq.utils.view.utils;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;

import com.wq.utils.view.widget.MsgView;


/**
 * 项目名称：    com.wq.smartcampus.approval.utils
 * 类描述           未读消息提示View,显示小红点或者带有数字的红点:
 *                  数字一位,圆
 *                  数字两位,圆角矩形,圆角是高度的一半
 *                  数字超过两位,显示99+
 *                  此工具类只适用于{@link com.wq.utils.view.widget.SlidingTabsLayout}组件的显示消息
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/9/19
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/9/19
 * 修改备注：
 */
public class UnreadMsgUtils {
    /**
     * 显示消息小红点
     * @param msgView
     * @param num
     */
    public static void show(MsgView msgView, int num) {
        if (msgView == null) {
            return;
        }
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) msgView.getLayoutParams();
        DisplayMetrics dm = msgView.getResources().getDisplayMetrics();
        msgView.setVisibility(View.VISIBLE);
        if (num <= 0) {//圆点,设置默认宽高
            msgView.setStrokeWidth(0);
            msgView.setText("");

            lp.width = (int) (5 * dm.density);
            lp.height = (int) (5 * dm.density);
            msgView.setLayoutParams(lp);
        } else {
            lp.height = (int) (18 * dm.density);
            if (num > 0 && num < 10) {//圆
                lp.width = (int) (18 * dm.density);
                msgView.setText(num + "");
            } else if (num > 9 && num < 100) {//圆角矩形,圆角是高度的一半,设置默认padding
                lp.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                msgView.setPadding((int) (6 * dm.density), 0, (int) (6 * dm.density), 0);
                msgView.setText(num + "");
            } else {//数字超过两位,显示99+
                lp.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                msgView.setPadding((int) (6 * dm.density), 0, (int) (6 * dm.density), 0);
                msgView.setText("99+");
            }
            msgView.setLayoutParams(lp);
        }
    }

    /**
     * 设置消息数量的尺寸
     * @param rtv       消息组件
     * @param size      尺寸
     */
    public static void setSize(MsgView rtv, int size) {
        if (rtv == null) {
            return;
        }
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) rtv.getLayoutParams();
        lp.width = size;
        lp.height = size;
        rtv.setLayoutParams(lp);
    }
}


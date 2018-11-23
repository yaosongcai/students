package com.wq.utils.view.widget;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 项目名称：    com.wq.utils.view.widget
 * 类描述        ViewPager 3d轮播效果
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/10
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/10
 * 修改备注：
 */
public class MyPagerTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.9F;

    @Override
    public void transformPage(View page, float position) {
        float rotate = 20 * Math.abs(position);
        float MINALPHA = 0.6f;


        float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
        if (position < -1) {

        } else if (position < 0) {
            //      page.setScaleX(1);
            page.setScaleY(scaleFactor);// y轴缩放
            page.setRotationY(rotate); // y轴旋转
            page.setAlpha(MINALPHA + (1 + position) * (1 - MINALPHA)); //设置透明度

        } else if (position >= 0 && position < 1) {
            //    page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(-rotate);
            page.setAlpha(MINALPHA + (1 - position) * (1 - MINALPHA));
        } else if (position >= 1) {
            //    page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(-rotate);
            page.setAlpha(MINALPHA + (1 + position) * (1 - MINALPHA));
        }
    }
}

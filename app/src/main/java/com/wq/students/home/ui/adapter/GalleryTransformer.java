package com.wq.students.home.ui.adapter;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 项目名称：    com.aj.adviewpager
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/10
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/10
 * 修改备注：
 */
public class GalleryTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View view, float position) {
        float scale = 0.3f;
        float scaleValue = 1 - Math.abs(position) * scale;
        view.setScaleX(scaleValue);
        view.setScaleY(scaleValue);
        view.setAlpha(scaleValue);
        view.setPivotX(view.getWidth() * (1 - position - (position > 0 ? 1 : -1) * 0.75f) * scale);
        view.setElevation(position > -0.25 && position < 0.25 ? 1 : 0);
    }
}

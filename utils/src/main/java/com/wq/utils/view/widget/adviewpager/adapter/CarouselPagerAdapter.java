package com.wq.utils.view.widget.adviewpager.adapter;

import android.support.annotation.IntRange;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.wq.utils.view.widget.adviewpager.CarouselViewPager;


/**
 * 项目名称：    com.aj.adviewpager.Adater
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/10
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/10
 * 修改备注：
 */
public abstract class CarouselPagerAdapter<V extends CarouselViewPager> extends PagerAdapter {
    /**
     * 系数，可以自行设置，但又以下原则需要遵循：
     * <ul>
     * <li>必须大于1</li>
     * <li>尽量小</li>
     * </ul>
     */
    private static final int COEFFICIENT = 10;
    private V mViewPager;

    public CarouselPagerAdapter(V viewPager) {
        this.mViewPager = viewPager;
    }

    /**
     * @return 实际数据数量
     */
    @IntRange(from = 0)
    public abstract int getRealDataCount();

    @Override
    public final int getCount() {
        long realDataCount = getRealDataCount();
        if (realDataCount > 1) {
            realDataCount = getRealDataCount() * COEFFICIENT;
            realDataCount = realDataCount > Integer.MAX_VALUE ? Integer.MAX_VALUE : realDataCount;
        }
        return (int) realDataCount;
    }

    @Override
    public final boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public final Object instantiateItem(ViewGroup container, int position) {
        position = position % getRealDataCount();
        return this.instantiateRealItem(container, position);
    }

    public abstract Object instantiateRealItem(ViewGroup container, int position);

    @Override
    public final void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public final void finishUpdate(ViewGroup container) {
        // 数量为1，不做position替换
        if (getCount() <= 1) {
            return;
        }

        int position = mViewPager.getCurrentItem();
        // ViewPager的更新即将完成，替换position，以达到无限循环的效果
        if (position == 0) {
            position = getRealDataCount();
            mViewPager.setCurrentItem(position, false);
        } else if (position == getCount() - 1) {
            position = getRealDataCount() - 1;
            mViewPager.setCurrentItem(position, false);
        }
    }
}

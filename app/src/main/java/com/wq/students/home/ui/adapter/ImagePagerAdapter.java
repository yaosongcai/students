package com.wq.students.home.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wq.students.R;
import com.wq.utils.view.widget.adviewpager.CarouselViewPager;
import com.wq.utils.view.widget.adviewpager.adapter.CarouselPagerAdapter;


/**
 * 项目名称：    com.aj.adviewpager.Adater
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/10
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/10
 * 修改备注：
 */
public class ImagePagerAdapter extends CarouselPagerAdapter<CarouselViewPager> {

    public ImagePagerAdapter(Context context, CarouselViewPager viewPager) {
        super(viewPager);
    }

    int[] imgRes = {
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3,
   /*         R.drawable.img_wallhaven_426244,
            R.drawable.img_wallhaven_431231,
            R.drawable.img_wallhaven_432740,
            R.drawable.img_wallhaven_426244,
            R.drawable.img_wallhaven_431231,
            R.drawable.img_wallhaven_432740,*/
    };

    @Override
    public Object instantiateRealItem(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setAdjustViewBounds(true);
        view.setImageResource(imgRes[position]);
        view.setLayoutParams(new LinearLayout.LayoutParams(900, 400));
        container.addView(view);
        return view;
    }

    @Override
    public int getRealDataCount() {
        return imgRes != null ? imgRes.length : 0;
    }
}
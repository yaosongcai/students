package com.wq.students.home.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 项目名称：    com.wq.students.home.ui.adapter
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/10
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/10
 * 修改备注：
 */
public class MyAdater extends PagerAdapter {
    private List<Integer> list;
    private Context context;

    public MyAdater(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);  //充满imageview
        iv.setImageResource(list.get(position));
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

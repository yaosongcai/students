package com.wq.students.reward.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wq.students.R;
import com.wq.students.reward.model.bean.RewardListBean;

/**
 * 项目名称：    com.wq.students.reward.ui.adapter
 * 类描述        适配器
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/20
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/20
 * 修改备注：
 */
public class RewardAdapter extends PagerAdapter {

    private static final int REWARD_NEWDATA = 0x960,//最新数据
            REWARD_HISTORYDATA = 0x961;//历史数据
    private LayoutInflater inflater;
    private Context context;
    private RewardListBean bean;
    private String[] titles;

    public RewardAdapter(Context context, RewardListBean bean, String[] titles) {
        this.context = context;
        this.bean = bean;
        this.titles = titles;
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(RewardListBean bean){
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.item_recyclerview, null);
        view.setLayoutManager(new LinearLayoutManager(context));


        RewardListAdapter adapter = null;
        if (getItemType(position) == REWARD_NEWDATA) {
            adapter = new RewardListAdapter(context,bean.getNewList());
        } else {
            adapter = new RewardListAdapter(context,bean.getList());
        }
        view.setAdapter(adapter);
        container.addView(view);
        view.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        return view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    private int getItemType(int position) {
        switch (position) {
            case 0:
                return REWARD_NEWDATA;
            default:
                return REWARD_HISTORYDATA;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}

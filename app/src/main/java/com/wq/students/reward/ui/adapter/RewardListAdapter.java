package com.wq.students.reward.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wq.students.R;
import com.wq.students.reward.model.bean.RewardPunishmentInfoBean;
import com.wq.utils.util.StringUtils;

import java.util.List;

/**
 * 项目名称：    com.wq.students.reward.ui.adapter
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/21
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/21
 * 修改备注：
 */
public class RewardListAdapter extends RecyclerView.Adapter<RewardListAdapter.ViewHolder> {

    private List<RewardPunishmentInfoBean> list;
    private Context context;

    public RewardListAdapter(Context context,List<RewardPunishmentInfoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_reward,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_title.setText(StringUtils.null2Length0(list.get(position).getPp_reason()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.beause.setText(Html.fromHtml("<font color='#707070'>奖惩原因：</font><myfont color='#555555'>" + StringUtils.null2Length0(list.get(position).getPp_reason()) + "</font>", Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.beause.setText(Html.fromHtml("<font color='#707070'>奖惩原因：</font><myfont color='#555555'>" + StringUtils.null2Length0(list.get(position).getPp_reason()) + "</font>"));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.deals.setText(Html.fromHtml("<font color='#707070'>奖惩明细：</font><myfont color='#555555'>" + StringUtils.null2Length0(list.get(position).getPp_detailed()) + "</font>", Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.deals.setText(Html.fromHtml("<font color='#707070'>奖惩明细：</font><myfont color='#555555'>" + StringUtils.null2Length0(list.get(position).getPp_detailed()) + "</font>"));
        }

        holder.tv_time.setText(StringUtils.null2Length0(list.get(position).getPp_date()));

        if (StringUtils.null2Length0(list.get(position).getReward_punishment_type()).equals("奖")) {
            holder.iv_icon.setImageResource(R.drawable.reward_2);
        } else {
            holder.iv_icon.setImageResource(R.drawable.reward_1);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        TextView beause;
        TextView deals;
        TextView tv_time;
        ImageView iv_icon;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            deals = itemView.findViewById(R.id.deals);
            beause = itemView.findViewById(R.id.beause);
            tv_time = itemView.findViewById(R.id.tv_time);
            iv_icon = itemView.findViewById(R.id.iv_icon);
        }
    }
}

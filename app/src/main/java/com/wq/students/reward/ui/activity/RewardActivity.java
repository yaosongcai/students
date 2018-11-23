package com.wq.students.reward.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.squareup.leakcanary.RefWatcher;
import com.wq.students.R;
import com.wq.students.base.base.BaseActivity;
import com.wq.students.base.di.component.AppComponent;
import com.wq.students.base.integration.cache.IntelligentCache;
import com.wq.students.base.utils.ArmsUtils;
import com.wq.students.component.DaggerRewardComponent;
import com.wq.students.module.RewardModule;
import com.wq.students.reward.contracts.RewardContract;
import com.wq.students.reward.model.bean.RewardListBean;
import com.wq.students.reward.presenter.RewardPersenter;
import com.wq.students.reward.ui.adapter.RewardAdapter;
import com.wq.students.reward.ui.widget.ObservableScrollView;
import com.wq.utils.util.BarUtils;
import com.wq.utils.util.ScreenUtils;
import com.wq.utils.util.SizeUtils;
import com.wq.utils.view.widget.CustomTitleBar;
import com.wq.utils.view.widget.SlidingTabsLayout;

import java.text.DecimalFormat;

import javax.inject.Inject;

import butterknife.BindView;

public class RewardActivity extends BaseActivity<RewardPersenter> implements RewardContract.View {

    private int mStatusBar;
    private int topLayoutHeight;
    private int maxDiff;
    @Inject
    RewardAdapter mRewardAdapter;

    @BindView(R.id.stl)
    SlidingTabsLayout stl;

//    @BindView(R.id.stl1)
//    SlidingTabsLayout stl1;

    @BindView(R.id.vp)
    ViewPager vp;

    @BindView(R.id.rl_title)
    RelativeLayout rl_title;

    @BindView(R.id.title)
    CustomTitleBar title;

    @BindView(R.id.ll_top)
    LinearLayout ll_top;

    @BindView(R.id.scrollView_main)
    ObservableScrollView scrollView_main;

    @BindView(R.id.rl_top)
    RelativeLayout rl_top;

    @BindView(R.id.view_status)
    View view_status;

    @BindView(R.id.rl_bottom)
    LinearLayout rl_bottom;

    @BindView(R.id.rl_content)
    RelativeLayout rl_content;

    @BindView(R.id.line)
    View line;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerRewardComponent.builder()
                .appComponent(appComponent)
                .rewardModule(new RewardModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_reward;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        mStatusBar = BarUtils.getStatusBarHeight();
        view_status.getLayoutParams().height = mStatusBar;
        topLayoutHeight = SizeUtils.dp2px(48) + mStatusBar + SizeUtils.getMeasuredHeight(stl) - SizeUtils.getMeasuredHeight(rl_bottom);
        vp.setMinimumWidth(ScreenUtils.getScreenHeight());
        vp.setAdapter(mRewardAdapter);
        stl.setViewPager(vp);
//        stl1.setViewPager(vp);

        mPresenter.queryRewardList();
        scrollView_main.setScrollViewListener(listener);

        ((RefWatcher) ArmsUtils.obtainAppComponentFromContext(getApplicationContext()).extras()
                .get(IntelligentCache.getKeyOfKeep(RefWatcher.class.getName()))).watch(this);

    }

    @Override
    public void initRewardNewList(RewardListBean been) {
        mRewardAdapter.setData(been);
    }

    private ObservableScrollView.ScrollViewListener listener = new ObservableScrollView.ScrollViewListener() {
        @Override
        public void onScrollChanged(NestedScrollView scrollView, int x, int y, int oldx, int oldy) {
            int[] location = new int[2];
            rl_bottom.getLocationOnScreen(location);
            int yPosition = location[1];

            if (yPosition <= topLayoutHeight) {
                ll_top.setVisibility(View.VISIBLE);
                rl_title.setVisibility(View.GONE);
            } else {
                ll_top.setVisibility(View.GONE);
                rl_title.setVisibility(View.VISIBLE);
            }

            Log.d("maxDiff", "yPosition = " + yPosition);
            Log.d("maxDiff", "topLayoutHeight = " + topLayoutHeight);
            Log.d("maxDiff", "yPosition - topLayoutHeight = " + (yPosition - topLayoutHeight));
//            if (yPosition - topLayoutHeight > maxDiff){
            maxDiff = yPosition - topLayoutHeight;
            Log.d("maxDiff", "maxDiff = " + maxDiff);
//            }
            Log.d("maxDiff", "====================================================================================");

            if (maxDiff <= 0 && ll_top.getChildCount() <= 1){
                rl_content.removeView(stl);
                rl_content.removeView(line);
                ll_top.addView(stl);
                ll_top.addView(line);
            } else if (maxDiff > 0 && rl_content.getChildCount() <= 1){
                ll_top.removeView(stl);
                ll_top.removeView(line);
                rl_content.addView(stl,1);
                rl_content.addView(line,2);
            }

            if (maxDiff <= 0) return;
            int diff = yPosition - topLayoutHeight;
            DecimalFormat df = new DecimalFormat("0.0");
//            PluLog.i("LHD  距离 = $diff  maxDiff = $maxDiff" + "   比例 = " + df.format(diff * 255 / maxDiff) + "  最终值 = " + df.format(diff * 255 / maxDiff))
//            if (diff >= 0) {
//                String valueLong = df.format(diff * 255 / maxDiff);
//                String value = valueLong.substring(0, valueLong.length() - 2);
////                PluLog.i("LHD 最终透明值 ： $value")
//                int alph = 255 - Integer.parseInt(value);
////                PluLog.i("LHD  透明值 = $alph    $value")
//                rl_top.setBackgroundColor(Color.argb(alph, 0, 156, 255));
//                rl_title.setBackgroundColor(Color.argb(alph, 0, 156, 255));
//            } else {
//                rl_top.setBackgroundColor(Color.argb(255, 0, 156, 255));
//                rl_title.setBackgroundColor(Color.argb(255, 0, 156, 255));
//            }

            //上拉加载
            if (y == (scrollView.getChildAt(0).getMeasuredHeight() - scrollView.getMeasuredHeight())) {
                Log.i("LHD", "LHHD >>>>>>>>>>>>>  滑动到底部");
//                fragmentOne.loadMore();
            }
        }
    };

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRewardAdapter = null;
    }
}

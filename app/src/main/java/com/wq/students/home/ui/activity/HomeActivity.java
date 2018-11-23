package com.wq.students.home.ui.activity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.leakcanary.RefWatcher;
import com.wq.students.R;
import com.wq.students.base.base.BaseActivity;
import com.wq.students.base.di.component.AppComponent;
import com.wq.students.base.integration.cache.IntelligentCache;
import com.wq.students.base.utils.ArmsUtils;
import com.wq.students.home.ui.fragment.HomeFragment;
import com.wq.students.home.ui.fragment.MineFragment;
import com.wq.utils.view.widget.HjmBottomTab;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements HomeFragment.OnFragmentInteractionListener,MineFragment.OnFragmentInteractionListener {

    @BindView(R.id.index_bottom_tab)
    HjmBottomTab indexBottomTab;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_home;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        indexBottomTab.initTab(this)
                //增加底部Tab，最后一个参数，为true时表示默认选中的Tab
                .addTab("首页", R.drawable.ic_home, HomeFragment.newInstance(), true)
                .addTab("我的", R.drawable.ic_my, MineFragment.newInstance())
                .setPaddingTop(4)//设置Tab上边的padding值
                .setPaddingBottom(4)//设置Tab下边的padding值
                .setFontSize(12)//设置文字大小
                //设置选中以及未选中的颜色
                .setSelectColor(Color.parseColor("#0090ff"), Color.parseColor("#686868"))
                //Tab背景颜色
                .setBackground(Color.WHITE)
                //Tab文字以及图片的间隔大小
                .show();

        ((RefWatcher) ArmsUtils.obtainAppComponentFromContext(getApplicationContext()).extras()
                .get(IntelligentCache.getKeyOfKeep(RefWatcher.class.getName()))).watch(this);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

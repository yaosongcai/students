package com.wq.students.schoolcalendar.ui;

import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.squareup.leakcanary.RefWatcher;
import com.wq.students.R;
import com.wq.students.base.base.BaseActivity;
import com.wq.students.base.di.component.AppComponent;
import com.wq.students.base.integration.cache.IntelligentCache;
import com.wq.students.base.utils.ArmsUtils;
import com.wq.utils.view.widget.CustomPopupWindow;

import butterknife.BindView;

public class CalendarsActivity extends BaseActivity implements CustomPopupWindow.CustomPopupWindowListener {

    private CustomPopupWindow mPupopWindow;

    @BindView(R.id.view)
    View view;

    @BindView(R.id.main_darkview)
    View layer;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_calendars;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        mPupopWindow = CustomPopupWindow.builder()
                .contentView(LayoutInflater.from(this).inflate(R.layout.item_recyclerview,null))
                .backgroundDrawable(new PaintDrawable())
                .animationStyle(R.style.PopupWindowAnimation)
                .isHWrap(true)
                .customListener(this)
                .layer(layer)
                .build();

        ((RefWatcher) ArmsUtils.obtainAppComponentFromContext(getApplicationContext()).extras()
                .get(IntelligentCache.getKeyOfKeep(RefWatcher.class.getName()))).watch(this);
    }

    @Override
    public void initPopupView(View contentView) {

    }

    @Override
    public void onDismiss() {

    }
}

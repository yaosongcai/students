package com.wq.students.achievement.ui.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;
import com.wq.students.R;
import com.wq.students.base.base.BaseActivity;
import com.wq.students.base.di.component.AppComponent;
import com.wq.students.base.integration.cache.IntelligentCache;
import com.wq.students.base.utils.ArmsUtils;
import com.wq.utils.view.widget.AchieveView;
import com.wq.utils.view.widget.CustomPopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AchieveActivity extends BaseActivity implements CustomPopupWindow.CustomPopupWindowListener {

    private CustomPopupWindow mPopupWindow;
    private TextView tv_address;
    private RecyclerView recyclerview;

    @BindView(R.id.av)
    AchieveView av;

    @BindView(R.id.view)
    View view;

    @BindView(R.id.main_darkview)
    View layer;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_achieve;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        av = (AchieveView) findViewById(R.id.av);
        av.setOnDrawListener(new AchieveView.OnDrawListener() {
            @Override
            public void onDrawListener(AchieveView.Values values, Paint paint) {
                if (values.row == 2 && values.page == 3){
                    paint.setColor(Color.parseColor("#ff6161"));
                }
            }
        });
        av.setTitle(Arrays.asList("科目","第一单元","第二单元","第三单元","中期","排名"));
        
        List<AchieveView.Column> data = new ArrayList<>();
        AchieveView.Column column = new AchieveView.Column();
        List<AchieveView.Values> valuesList = new ArrayList<>();

        for (int i = 0; i < 6;i++){
            AchieveView.Values values = new AchieveView.Values();
            if (i == 0){
                values.value = "数学";
            } else {
                values.value = "9" + i;
                values.msg = "+1";
            }
            valuesList.add(values);
        }

        column.values = valuesList;
        data.add(column);

        AchieveView.Column column1 = new AchieveView.Column();
        List<AchieveView.Values> valuesList1 = new ArrayList<>();

        for (int i =0;i<6;i++){
            AchieveView.Values values = new AchieveView.Values();
            if (i == 0){
                values.value = "语文";
            } else {
                values.value = "9" + i;
                values.msg = "1";
            }
            valuesList1.add(values);
        }

        column1.values = valuesList1;
        data.add(column1);

        av.setData(data);

        mPopupWindow = CustomPopupWindow.builder()
                .contentView(LayoutInflater.from(this).inflate(R.layout.popupwindow_recyclerview,null))
                .backgroundDrawable(new PaintDrawable())
                .animationStyle(R.style.PopupWindowAnimation)
                .isHWrap(true)
                .customListener(this)
                .layer(layer)
                .build();

        ((RefWatcher) ArmsUtils.obtainAppComponentFromContext(getApplicationContext()).extras()
                .get(IntelligentCache.getKeyOfKeep(RefWatcher.class.getName()))).watch(this);
    }

    @OnClick({R.id.rl_left,R.id.rl_right})
    void click(View view){
        switch (view.getId()){
            case R.id.rl_left:
                break;
            case R.id.rl_right:
                mPopupWindow.showAsDropDown(this.view);
                break;
        }
    }

    @Override
    public void initPopupView(View contentView) {
        tv_address = contentView.findViewById(R.id.tv_address);
        recyclerview = contentView.findViewById(R.id.recyclerView);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));

//        CommonAdapter<>
    }

    @Override
    public void onDismiss() {

    }
}

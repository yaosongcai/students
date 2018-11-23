package com.wq.students.timetable.ui.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wq.students.R;
import com.wq.students.base.base.BaseActivity;
import com.wq.students.base.di.component.AppComponent;
import com.wq.students.component.DaggerTimetableComponent;
import com.wq.students.module.TimetableModule;
import com.wq.students.timetable.contract.TimetableContract;
import com.wq.students.timetable.model.bean.StudentClassManagementBean;
import com.wq.students.timetable.model.bean.TermBean;
import com.wq.students.timetable.presenter.TimetablePersenter;
import com.wq.students.timetable.ui.widget.ChildBean;
import com.wq.students.timetable.ui.widget.ClassOptionDialog;
import com.wq.students.timetable.ui.widget.Colum;
import com.wq.students.timetable.ui.widget.TablesView;
import com.wq.students.timetable.ui.widget.Values;
import com.wq.utils.util.StringUtils;
import com.wq.utils.view.widget.CustomPopupWindow;
import com.wq.utils.view.widget.commonAdapter.adapter.CommonAdapter;
import com.wq.utils.view.widget.commonAdapter.adapter.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class TimetableActivity extends BaseActivity<TimetablePersenter> implements TimetableContract.View, CustomPopupWindow.CustomPopupWindowListener, ValueAnimator.AnimatorUpdateListener, ClassOptionDialog.OnChangeListener {

    private int state;
    private CustomPopupWindow mPopupWindow;
    private ValueAnimator startAnimator;
    private ValueAnimator endAnimator;
    @Inject
    CommonAdapter<TermBean> mAdapter;
    @Inject
    List<TermBean> mTermBeen;
    @Inject
    List<StudentClassManagementBean> mClassList;
    @Inject
    ClassOptionDialog mDialog;

    @BindView(R.id.iv_arr)
    ImageView iv_arr;

    @BindView(R.id.tv)
    TablesView tv;

    @BindView(R.id.ll_top)
    RelativeLayout ll_top;

    @BindView(R.id.tv_quarter)
    TextView tv_quarter;

    @BindView(R.id.main_darkview)
    View layer;

    @BindView(R.id.tv_className)
    TextView tv_className;

    @BindView(R.id.iv_dropUp)
    ImageView iv_dropUp;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerTimetableComponent.builder()
                .timetableModule(new TimetableModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_timetable;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        List<Colum> datas = new ArrayList<>();
        Colum colum = new Colum();
        Values tabValue = new Values();
        tabValue.content = "";
        tabValue.isMerge = false;
        colum.leftTable = tabValue;
        colum.isTopTable = true;

        List<Values> values = new ArrayList<>();
        Values v1 = new Values();
        v1.content = "星期一";
        values.add(v1);

        Values v2 = new Values();
        v2.content = "星期二";
        values.add(v2);

        Values v3 = new Values();
        v3.content = "星期三";
        values.add(v3);

        Values v4 = new Values();
        v4.content = "星期四";
        values.add(v4);

        Values v5 = new Values();
        v5.content = "星期五";
        values.add(v5);

        List<ChildBean> childBeen = new ArrayList<>();
        ChildBean bean = new ChildBean();
        bean.values = values;
        childBeen.add(bean);

        colum.conotent = childBeen;

        datas.add(colum);

        Colum colum1 = new Colum();
        Values tabValue1 = new Values();
        tabValue1.content = "早自习";
        tabValue1.isMerge = false;
        colum1.leftTable = tabValue1;
        colum1.isTopTable = false;

        List<Values> values1 = new ArrayList<>();
        Values vv1 = new Values();
        vv1.content = "语文";
        values1.add(vv1);

        Values vv2 = new Values();
        vv2.content = "数学";
        values1.add(vv2);

        Values vv3 = new Values();
        vv3.content = "英语";
        values1.add(vv3);

        Values vv4 = new Values();
        vv4.content = "语文";
        values1.add(vv4);

        Values vv5 = new Values();
        vv5.content = "数学";
        values1.add(vv5);

        List<ChildBean> childBeen1 = new ArrayList<>();
        ChildBean bean1 = new ChildBean();
        bean1.values = values1;
        childBeen1.add(bean1);

        colum1.conotent = childBeen1;
        datas.add(colum1);

        Colum colum2 = new Colum();
        colum2.isMerge = true;
        Values values2 = new Values();
        values2.content = "早休时间";
        colum2.mergeContent = values2;
        datas.add(colum2);

        Colum colum3 = new Colum();
        List<ChildBean> beanList = new ArrayList<>();
        ChildBean bean2 = new ChildBean();
        beanList.add(bean2);
        Values values3 = new Values();
        values3.content = "上午";
        colum3.leftTable = values3;
        colum3.conotent = beanList;

        List<Values> values4 = new ArrayList<>();
        bean2.values = values4;
        Values vvv1 = new Values();
        vvv1.content = "英文";
        values4.add(vvv1);

        Values vvv2 = new Values();
        vvv2.content = "英文";
        values4.add(vvv2);

        Values vvv3 = new Values();
        vvv3.content = "英文";
        values4.add(vvv3);

        Values vvv4 = new Values();
        vvv4.content = "英文";
        values4.add(vvv4);

        Values vvv5 = new Values();
        vvv5.content = "英文";
        values4.add(vvv5);

        ChildBean bean3 = new ChildBean();
        beanList.add(bean3);
        Values values5 = new Values();
        values5.content = "上午";
        colum3.leftTable = values5;
        colum3.conotent = beanList;

        List<Values> values6 = new ArrayList<>();
        bean3.values = values6;
        Values vvvv1 = new Values();
        vvvv1.content = "英文";
        values6.add(vvvv1);

        Values vvvv2 = new Values();
        vvvv2.content = "英文";
        values6.add(vvvv2);

        Values vvvv3 = new Values();
        vvvv3.content = "英文";
        values6.add(vvvv3);

        Values vvvv4 = new Values();
        vvvv4.content = "英文";
        values6.add(vvvv4);

        Values vvvv5 = new Values();
        vvvv5.content = "英文";
        values6.add(vvvv5);

        ChildBean bean4 = new ChildBean();
        bean4.content = "课间操";
        bean4.isMerge = true;
        beanList.add(bean4);

        datas.add(colum3);
        tv.setData(datas);

        mPresenter.queryTermList();

        mPopupWindow = CustomPopupWindow.builder()
                .contentView(LayoutInflater.from(this).inflate(R.layout.item_recyclerview, null))
                .backgroundDrawable(new PaintDrawable())
                .isFocus(true)
                .customListener(this)
                .isHWrap(true)
                .layer(layer)
                .animationStyle(R.style.PopupWindowAnimation)
                .build();

        startAnimator = ValueAnimator.ofFloat(0, 180);
        rotationExpandIcon(startAnimator);

        endAnimator = ValueAnimator.ofFloat(180, 0);
        rotationExpandIcon(endAnimator);

        mDialog.setOnChangeListener(this);
    }

    @OnClick({R.id.rl_class, R.id.ll_top})
    void click(View view) {
        switch (view.getId()) {
            case R.id.ll_top:
                state = 1;
                mPopupWindow.showAsDropDown(ll_top);
                startAnimator.start();
                break;
            case R.id.rl_class:
                state = 2;
                if (mClassList == null) {
                    return;
                }
                mDialog.show();
                break;
        }
    }

    @Override
    public void initPopupWindow(List<TermBean> list) {
        if (list == null) {
            return;
        }

        if (list.size() > 0) {
            tv_quarter.setText(StringUtils.null2Length0(list.get(0).getTitles()));
            list.get(0).setClick(true);
        }

        mTermBeen.clear();
        mTermBeen.addAll(list);
        mAdapter.setDatas(mTermBeen);
    }

    @Override
    public void initClassList(List<StudentClassManagementBean> list) {
        if (list == null) {
            return;
        }

        if (list.size() > 0) {
            tv_className.setText(list.get(0).getClassCode());
        }

        mClassList.clear();
        mClassList.addAll(list);
        mDialog.setDatas(mClassList);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void initPopupView(View contentView) {
        if (contentView instanceof RecyclerView) {
            ((RecyclerView) contentView).setAdapter(mAdapter);
            ((RecyclerView) contentView).setLayoutManager(new LinearLayoutManager(this));
        }

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                for (TermBean bean : mTermBeen
                        ) {
                    bean.setClick(false);
                }
                mTermBeen.get(position).setClick(true);
                mAdapter.notifyDataSetChanged();
                tv_quarter.setText(StringUtils.null2Length0(mTermBeen.get(position).getTitles()));
                mPresenter.queryCourseList(mTermBeen.get(position).getId(), "");
                mPopupWindow.dismiss();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    public void onDismiss() {
        endAnimator.start();
    }

    private void rotationExpandIcon(ValueAnimator animator) {
        animator.setDuration(150);
        animator.setInterpolator(new LinearOutSlowInInterpolator());
        animator.addUpdateListener(this);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        if (state == 1) {
            iv_arr.setRotation((Float) animation.getAnimatedValue());
        } else {
            iv_dropUp.setRotation((Float) animation.getAnimatedValue());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDialog = null;
        mTermBeen = null;
        mClassList = null;
        mAdapter = null;
        mPopupWindow = null;
        startAnimator = null;
        endAnimator = null;
    }

    @Override
    public void onChange(StudentClassManagementBean bean) {
        mPresenter.queryCourseList("", bean.getId());
    }
}

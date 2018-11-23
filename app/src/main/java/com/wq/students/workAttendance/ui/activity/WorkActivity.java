package com.wq.students.workAttendance.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.wq.students.R;
import com.wq.students.base.base.BaseActivity;
import com.wq.students.base.di.component.AppComponent;
import com.wq.students.workAttendance.ui.fragment.WorkFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WorkActivity extends BaseActivity implements WorkFragment.OnFragmentInteractionListener {

    private MyAdapter adapter;
    private List<Fragment> mFragments = new ArrayList<>();

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_work;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mFragments.add(WorkFragment.newInstance(null,null));
        mFragments.add(WorkFragment.newInstance(null,null));
        adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}

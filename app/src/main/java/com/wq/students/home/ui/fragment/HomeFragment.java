package com.wq.students.home.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.wq.students.R;
import com.wq.students.achievement.ui.activity.AchieveActivity;
import com.wq.students.base.base.BaseFragment;
import com.wq.students.base.di.component.AppComponent;
import com.wq.students.home.presenter.HomePersenter;
import com.wq.students.home.ui.widget.CustomWeekBar;
import com.wq.students.reward.ui.activity.RewardActivity;
import com.wq.students.timetable.ui.activity.TimetableActivity;
import com.wq.students.workAttendance.ui.activity.WorkActivity;
import com.wq.utils.util.BarUtils;
import com.wq.utils.util.SizeUtils;
import com.wq.utils.view.widget.calendarview.CalendarView;
import com.wq.utils.view.widget.commonAdapter.adapter.CommonAdapter;
import com.wq.utils.view.widget.commonAdapter.adapter.base.ViewHolder;
import com.wq.utils.view.widget.commonAdapter.bean.RescueContentBean;
import com.wq.utils.view.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment<HomePersenter> implements AppBarLayout.OnOffsetChangedListener {

    private CommonAdapter<RescueContentBean> mAdapter;
    private OnFragmentInteractionListener mListener;

    @BindView(R.id.xbanner)
    XBanner mBanner;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @BindView(R.id.abl_bar)
    AppBarLayout abl_bar;

    @BindView(R.id.tb)
    Toolbar tb;

    @BindView(R.id.title)
    RelativeLayout title;

    @BindView(R.id.vf)
    ViewFlipper vf;

    @BindView(R.id.calendarView)
    CalendarView calendarView;

    @BindView(R.id.tv_date)
    TextView tv_date;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            mBanner.setClipChildrenTopMargin(SizeUtils.dp2px(10) + BarUtils.getStatusHeight(getContext()));
        }

        calendarView.setWeekBar(CustomWeekBar.class);

        abl_bar.addOnOffsetChangedListener(this);

        final List<Integer> bannerList = new ArrayList<>();
        bannerList.add(R.drawable.banner1);
        bannerList.add(R.drawable.banner2);
        bannerList.add(R.drawable.banner3);
        mBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ((ImageView) view).setImageResource(bannerList.get(position));
            }
        });
        mBanner.setData(bannerList, null);

        List<RescueContentBean> datas = new ArrayList<>();
        datas.add(new RescueContentBean(R.drawable.icon_nav1, getContext().getResources().getString(R.string.Timetable), TimetableActivity.class, 0));
        datas.add(new RescueContentBean(R.drawable.icon_nav2, getContext().getResources().getString(R.string.School_calendar), null, 1));
        datas.add(new RescueContentBean(R.drawable.icon_nav3, getContext().getResources().getString(R.string.achievement), AchieveActivity.class, 2));
        datas.add(new RescueContentBean(R.drawable.icon_nav4, getContext().getResources().getString(R.string.Check_work_attendance), WorkActivity.class, 3));
        datas.add(new RescueContentBean(R.drawable.icon_nav5, getContext().getResources().getString(R.string.Reward_and_punishment), RewardActivity.class, 4));
        recycleView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new CommonAdapter<RescueContentBean>(getContext(), R.layout.item_rescue_content) {
            @Override
            protected void convert(ViewHolder holder, final RescueContentBean bean, int position) {
                holder.setText(R.id.tv_content, bean.getTitle());
                holder.setImageResource(R.id.iv_icon, bean.getIcon());
                holder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (bean.getaClass() != null) {
                            Intent intent = new Intent(getContext(),bean.getaClass());
                            startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public void onViewHolderCreated(ViewHolder holder, View itemView) {
                super.onViewHolderCreated(holder, itemView);
            }
        };
        recycleView.setAdapter(mAdapter);
        mAdapter.setDatas(datas);

        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                tv_date.setText(year + "年" + month + "月");
            }
        });
    }

    @OnClick({R.id.iv_left,R.id.iv_right})
    void click(View view){
        switch (view.getId()){
            case R.id.iv_left:
                calendarView.scrollToPre();
                break;
            case R.id.iv_right:
                calendarView.scrollToNext();
                break;
        }
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int offset = Math.abs(verticalOffset);
        int total = appBarLayout.getTotalScrollRange();
        if (offset <= total / 2) {
            title.setBackgroundColor(getContext().getResources().getColor(R.color.transparent));
            mBanner.setVisibility(View.VISIBLE);
            title.setVisibility(View.GONE);
        } else {
            title.setVisibility(View.VISIBLE);
            title.setBackgroundColor(0x0078FF);
            tb.setBackgroundColor(0x0078FF);
            mBanner.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

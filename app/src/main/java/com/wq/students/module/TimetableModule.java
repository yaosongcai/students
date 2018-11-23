package com.wq.students.module;

import android.graphics.Color;

import com.wq.students.R;
import com.wq.students.base.di.scope.ActivityScope;
import com.wq.students.timetable.contract.TimetableContract;
import com.wq.students.timetable.model.TimetableModel;
import com.wq.students.timetable.model.bean.StudentClassManagementBean;
import com.wq.students.timetable.model.bean.TermBean;
import com.wq.students.timetable.ui.widget.ClassOptionDialog;
import com.wq.utils.util.StringUtils;
import com.wq.utils.view.widget.commonAdapter.adapter.CommonAdapter;
import com.wq.utils.view.widget.commonAdapter.adapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * 项目名称：    com.wq.students.module
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/19
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/19
 * 修改备注：
 */
@Module
public class TimetableModule {

    private TimetableContract.View view;

    public TimetableModule(TimetableContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TimetableContract.View getView(){
        return view;
    }

    @ActivityScope
    @Provides
    TimetableContract.Model provideModel(TimetableModel model){
        return model;
    }

    @ActivityScope
    @Provides
    List<TermBean> provideTermBeen(){
        return new ArrayList();
    }

    @ActivityScope
    @Provides
    ClassOptionDialog provideDialog(){
        return new ClassOptionDialog(view.getActivity());
    }

    @ActivityScope
    @Provides
    List<StudentClassManagementBean> provideStudentClassList(){
        return new ArrayList();
    }

    @ActivityScope
    @Provides
    CommonAdapter<TermBean> provideAdapter(final List<TermBean> list){
        final CommonAdapter<TermBean> adapter = new CommonAdapter<TermBean>(view.getActivity(),R.layout.item_text) {
            @Override
            protected void convert(ViewHolder holder, TermBean termBean, final int position) {
                holder.setText(R.id.item_text, StringUtils.null2Length0(termBean.getTitles()));

                if (list.get(position).isClick()){
                    holder.setBackgroundColor(R.id.item_text, Color.parseColor("#edeaeb"));
                } else {
                    holder.setBackgroundColor(R.id.item_text,Color.parseColor("#ffffff"));
                }
            }
        };
        adapter.setDatas(list);
        return adapter;
    }
}

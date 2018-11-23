package com.wq.students.timetable.contract;

import android.app.Activity;

import com.wq.students.base.mvp.IModel;
import com.wq.students.base.mvp.IView;
import com.wq.students.timetable.model.bean.StudentClassManagementBean;
import com.wq.students.timetable.model.bean.TermBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;


/**
 * 项目名称：    com.wq.student.login.contracts
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/9/29
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/9/29
 * 修改备注：
 */
public interface TimetableContract {

    interface View extends IView {

        /**
         *
         * 初始化顶部下拉菜单
         * @param list      下拉菜单列表数据
         * @author ysc
         * @time 2018/11/20 14:50
         */
        void initPopupWindow(List<TermBean> list);

        /**
         * 初始化班级弹窗菜单
         * @param list      班级弹窗菜单数据
         * @author ysc
         * @time 2018/11/20 14:50
         */
        void initClassList(List<StudentClassManagementBean> list);

        Activity getActivity();
    }

    interface Model extends IModel {

        /**
         *
         * 查询学期
         * @author ysc
         * @time 2018/11/19 11:58
         */
        Observable<ResponseBody> queryClassList();

        /**
         *
         * 查询学期
         * @author ysc
         * @time 2018/11/19 11:58
         */
        Observable<ResponseBody> queryTermList();

        /**
         *
         * 查询课表
         * @param classid       班级id
         * @param termid        学期id
         * @author ysc
         * @time 2018/11/19 12:00
         */
        Observable<ResponseBody> queryStCourseList(String termid,String classid);
    }
}

package com.wq.students.timetable.presenter;


import com.google.gson.reflect.TypeToken;
import com.wq.students.base.di.scope.ActivityScope;
import com.wq.students.base.mvp.BasePresenter;
import com.wq.students.base.utils.HttpUtils;
import com.wq.students.base.utils.OnSuccessAndFaultListener;
import com.wq.students.base.utils.OnSuccessAndFaultSub;
import com.wq.students.timetable.contract.TimetableContract;
import com.wq.students.timetable.model.bean.StudentClassManagementBean;
import com.wq.students.timetable.model.bean.TermBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * 项目名称：    com.wq.student.login.presenter
 * 类描述        主页面主导器
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/9/29
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/9/29
 * 修改备注：
 */
@ActivityScope
public class TimetablePersenter extends BasePresenter<TimetableContract.Model,TimetableContract.View> {

    private String termid = "";
    private String classid = "";
    @Inject
    List<TermBean> mTermBeen;
    @Inject
    List<StudentClassManagementBean> mStudentClassBeen;

    @Inject
    public TimetablePersenter(TimetableContract.Model model, TimetableContract.View view){
        super(model,view);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void queryTermList(){
        DisposableObserver d = new OnSuccessAndFaultSub<List<TermBean>>(new OnSuccessAndFaultListener<List<TermBean>>() {
            @Override
            public void onSuccess(List<TermBean> termBeen) {
                mTermBeen = termBeen;
                mRootView.initPopupWindow(termBeen);
                queryClassList();
            }

            @Override
            public void onFault(String errorMsg) {

            }
        },new TypeToken<List<TermBean>>(){}.getType());

        HttpUtils.getInstance().toSubscribe(mModel.queryTermList(),
                d);
    }

    public void queryClassList(){
        DisposableObserver d = new OnSuccessAndFaultSub<List<StudentClassManagementBean>>(new OnSuccessAndFaultListener<List<StudentClassManagementBean>>() {
            @Override
            public void onSuccess(List<StudentClassManagementBean> studentClassManagementBeen) {
                mStudentClassBeen = studentClassManagementBeen;
                mRootView.initClassList(studentClassManagementBeen);

                try {
                    queryCourseList(mTermBeen.get(0).getId(),mStudentClassBeen.get(0).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    queryCourseList("","");
                }
            }

            @Override
            public void onFault(String errorMsg) {

            }
        },new TypeToken<List<StudentClassManagementBean>>(){}.getType());

        HttpUtils.getInstance().toSubscribe(mModel.queryClassList(),
                d);

        mCompositeDisposable.add(d);
    }

    public void queryCourseList(String termid,String classid){
        this.termid = termid.isEmpty()?this.termid:termid;
        this.classid = classid.isEmpty()?this.classid:classid;

        DisposableObserver d = new OnSuccessAndFaultSub<String>(new OnSuccessAndFaultListener<String>() {
            @Override
            public void onSuccess(String studentClassManagementBean) {
                String result = studentClassManagementBean;
            }

            @Override
            public void onFault(String errorMsg) {

            }
        },String.class);

        HttpUtils.getInstance().toSubscribe(mModel.queryStCourseList(this.termid,this.classid),
                d);

        mCompositeDisposable.add(d);
    }
}

package com.wq.students.timetable.model;

import com.wq.students.base.di.scope.ActivityScope;
import com.wq.students.base.integration.IRepositoryManager;
import com.wq.students.base.mvp.BaseModel;
import com.wq.students.timetable.contract.TimetableContract;
import com.wq.students.timetable.model.api.TimetableApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * 项目名称：    com.wq.students.timetable.model
 * 类描述        课表页面model
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/19
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/19
 * 修改备注：
 */
@ActivityScope
public class TimetableModel extends BaseModel implements TimetableContract.Model {

    @Inject
    public TimetableModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<ResponseBody> queryClassList() {
        return mRepositoryManager.obtainRetrofitService(TimetableApi.class)
                .queryClassList();
    }

    @Override
    public Observable<ResponseBody> queryTermList() {
        return mRepositoryManager.obtainRetrofitService(TimetableApi.class)
                .queryTermList();
    }

    @Override
    public Observable<ResponseBody> queryStCourseList(String termid, String classid) {
        return mRepositoryManager.obtainRetrofitService(TimetableApi.class)
                .queryStCoruseLists(termid,classid);
    }
}

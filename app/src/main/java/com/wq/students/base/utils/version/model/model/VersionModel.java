package com.wq.students.base.utils.version.model.model;


import com.wq.students.base.integration.IRepositoryManager;
import com.wq.students.base.mvp.BaseModel;
import com.wq.students.base.utils.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * 项目名称：    com.wq.students.base.utils.version.model.model
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/8
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/8
 * 修改备注：
 */
public class VersionModel extends BaseModel {

    public VersionModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    /**
     *
     * 获取数据
     * @author ysc
     * @time 2018/10/9 11:56
     */
    public static void detectionVersion(String versionCode, String loginType, DisposableObserver<ResponseBody> subscriber){
        Observable<ResponseBody> observable = HttpUtils.getInstance().getHttpApi().detectionVersion(versionCode,loginType);
        HttpUtils.getInstance().toSubscribe(observable,subscriber);
    }

}

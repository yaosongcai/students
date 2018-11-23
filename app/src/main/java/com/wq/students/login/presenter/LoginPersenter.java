package com.wq.students.login.presenter;


import com.wq.students.base.di.scope.ActivityScope;
import com.wq.students.base.mvp.BasePresenter;
import com.wq.students.base.utils.version.VersionUtils;
import com.wq.students.login.contracts.LoginContract;

import javax.inject.Inject;

/**
 * 项目名称：    com.wq.student.login.presenter
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/9/29
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/9/29
 * 修改备注：
 */
@ActivityScope
public class LoginPersenter extends BasePresenter<LoginContract.Model,LoginContract.View> {

    private VersionUtils versionUtils;

    @Inject
    public LoginPersenter(LoginContract.Model model,LoginContract.View view){
        super(model,view);

        versionUtils = new VersionUtils();
    }

    public void checkVersion(){
        versionUtils.checkUpdate(1,false,false);
    }

}

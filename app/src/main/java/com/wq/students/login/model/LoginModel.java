package com.wq.students.login.model;


import com.wq.students.base.di.scope.ActivityScope;
import com.wq.students.base.integration.IRepositoryManager;
import com.wq.students.base.mvp.BaseModel;
import com.wq.students.login.contracts.LoginContract;

import javax.inject.Inject;

/**
 * 项目名称：    com.wq.student.login.model
 * 类描述        登陆模块
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/9/29
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/9/29
 * 修改备注：
 */
@ActivityScope
public class LoginModel extends BaseModel implements LoginContract.Model {

    @Inject
    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}

package com.wq.students.module;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wq.students.base.di.scope.ActivityScope;
import com.wq.students.login.contracts.LoginContract;
import com.wq.students.login.model.LoginModel;

import dagger.Module;
import dagger.Provides;

/**
 * 项目名称：    com.wq.student.module
 * 类描述        登陆模块
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/9/29
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/9/29
 * 修改备注：
 */
@Module
public class LoginModule {

    private LoginContract.View view;

    /**
     * 构建 LoginModule 时,将 View 的实现类传进来,这样就可以提供 View 的实现类给 Presenter
     *
     * @param view
     */
    public LoginModule(LoginContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    LoginContract.Model provideLoginModel(LoginModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    LoginContract.View getLoginView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    RxPermissions provideRxPermissions() {
        return new RxPermissions(view.getActivity());
    }

}

package com.wq.students.component;


import com.wq.students.base.di.component.AppComponent;
import com.wq.students.base.di.scope.ActivityScope;
import com.wq.students.login.ui.activity.LoginActivity;
import com.wq.students.module.LoginModule;

import dagger.Component;

/**
 * 项目名称：    com.wq.student.component
 * 类描述        注入登陆模块
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/9/29
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/9/29
 * 修改备注：
 */
@ActivityScope
@Component(modules = LoginModule.class,dependencies = AppComponent.class    )
public interface LoginComponent {
    void inject(LoginActivity activity);
}

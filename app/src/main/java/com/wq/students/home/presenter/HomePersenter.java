package com.wq.students.home.presenter;


import com.wq.students.base.di.scope.ActivityScope;
import com.wq.students.base.mvp.BasePresenter;
import com.wq.students.home.contracts.HomeContract;

import javax.inject.Inject;

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
public class HomePersenter extends BasePresenter<HomeContract.Model,HomeContract.View> {

    @Inject
    public HomePersenter(HomeContract.Model model, HomeContract.View view){
        super(model,view);

    }

}

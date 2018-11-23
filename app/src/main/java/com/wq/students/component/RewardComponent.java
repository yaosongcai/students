package com.wq.students.component;

import com.wq.students.base.di.component.AppComponent;
import com.wq.students.base.di.scope.ActivityScope;
import com.wq.students.module.RewardModule;
import com.wq.students.reward.ui.activity.RewardActivity;

import dagger.Component;

/**
 * 项目名称：    com.wq.students.component
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/21
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/21
 * 修改备注：
 */
@ActivityScope
@Component(modules = RewardModule.class,dependencies = AppComponent.class)
public interface RewardComponent {

    void inject(RewardActivity activity);
}

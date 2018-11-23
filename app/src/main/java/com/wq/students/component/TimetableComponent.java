package com.wq.students.component;

import com.wq.students.base.di.component.AppComponent;
import com.wq.students.base.di.scope.ActivityScope;
import com.wq.students.module.TimetableModule;
import com.wq.students.timetable.ui.activity.TimetableActivity;

import dagger.Component;

/**
 * 项目名称：    com.wq.students.component
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/19
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/19
 * 修改备注：
 */
@ActivityScope
@Component(modules = TimetableModule.class,dependencies = AppComponent.class)
public interface TimetableComponent {

    void inject(TimetableActivity activity);
}

package com.wq.students.login.contracts;

import android.app.Activity;

import com.wq.students.base.mvp.IModel;
import com.wq.students.base.mvp.IView;


/**
 * 项目名称：    com.wq.student.login.contracts
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/9/29
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/9/29
 * 修改备注：
 */
public interface LoginContract {

    interface View extends IView {

        Activity getActivity();
    }

    interface Model extends IModel {

    }
}

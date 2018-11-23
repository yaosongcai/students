package com.wq.students.reward.contracts;

import android.app.Activity;

import com.wq.students.base.mvp.IModel;
import com.wq.students.base.mvp.IView;
import com.wq.students.reward.model.bean.RewardListBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * 项目名称：    com.wq.students.reward.contracts
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/21
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/21
 * 修改备注：
 */
public interface RewardContract {

    public interface View extends IView{

        /**
         * 初始化数据
         * @param been
         * @author ysc
         * @time 2018/11/21 10:29
         */
        void initRewardNewList(RewardListBean been);

        Activity getActivity();
    }

    public interface Model extends IModel{

        /**
         *
         * 查询奖惩数据列表
         * @author ysc
         * @time 2018/11/21 9:44
         */
        Observable<ResponseBody> queryRewardList();

        /**
         *
         * 获取最新奖罚信息列表
         * @author ysc
         * @time 2018/11/21 9:55
         */
        Observable<ResponseBody> queryRewardNewList();
    }
}

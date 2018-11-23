package com.wq.students.module;

import com.wq.students.base.di.scope.ActivityScope;
import com.wq.students.reward.contracts.RewardContract;
import com.wq.students.reward.model.RewardModel;
import com.wq.students.reward.model.bean.RewardListBean;
import com.wq.students.reward.model.bean.RewardPunishmentInfoBean;
import com.wq.students.reward.ui.adapter.RewardAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * 项目名称：    com.wq.students.module
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/21
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/21
 * 修改备注：
 */
@Module
public class RewardModule {

    private RewardContract.View view;

    public RewardModule(RewardContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    RewardContract.View getView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    RewardContract.Model provideModel(RewardModel model){
        return model;
    }

    @ActivityScope
    @Provides
    List<RewardPunishmentInfoBean> provideList(){
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    String[] provideTitle(){
        return new String[]{
                "最近一次", "历史记录"
        };
    }

    @ActivityScope
    @Provides
    RewardListBean provideRewardListBean(){
        RewardListBean bean = new RewardListBean();
        bean.setList(new ArrayList<RewardPunishmentInfoBean>());
        bean.setNewList(new ArrayList<RewardPunishmentInfoBean>());
        return bean;
    }

    @ActivityScope
    @Provides
    RewardAdapter provideAdapter(RewardListBean bean,String[] title){
        return new RewardAdapter(view.getActivity(),bean,title);
    }
}

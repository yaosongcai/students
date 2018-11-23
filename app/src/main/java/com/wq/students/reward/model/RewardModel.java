package com.wq.students.reward.model;

import com.wq.students.base.di.scope.ActivityScope;
import com.wq.students.base.integration.IRepositoryManager;
import com.wq.students.base.mvp.BaseModel;
import com.wq.students.reward.contracts.RewardContract;
import com.wq.students.reward.model.api.RewardApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * 项目名称：    com.wq.students.reward.model
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/21
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/21
 * 修改备注：
 */
@ActivityScope
public class RewardModel extends BaseModel implements RewardContract.Model {

    @Inject
    public RewardModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<ResponseBody> queryRewardList() {
        return mRepositoryManager.obtainRetrofitService(RewardApi.class)
                .queryRewardList();
    }

    @Override
    public Observable<ResponseBody> queryRewardNewList() {
        return mRepositoryManager.obtainRetrofitService(RewardApi.class)
                .queryRewardNewList();
    }
}

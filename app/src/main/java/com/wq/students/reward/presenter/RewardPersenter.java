package com.wq.students.reward.presenter;

import com.wq.students.base.di.scope.ActivityScope;
import com.wq.students.base.mvp.BasePresenter;
import com.wq.students.base.utils.HttpUtils;
import com.wq.students.base.utils.OnSuccessAndFaultListener;
import com.wq.students.base.utils.OnSuccessAndFaultSub;
import com.wq.students.reward.contracts.RewardContract;
import com.wq.students.reward.model.bean.RewardInfoBean;
import com.wq.students.reward.model.bean.RewardListBean;
import com.wq.students.reward.model.bean.RewardNewInfoBean;
import com.wq.students.reward.model.bean.RewardPunishmentInfoBean;
import com.wq.utils.util.ToastUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * 项目名称：    com.wq.students.reward.presenter
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/21
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/21
 * 修改备注：
 */
@ActivityScope
public class RewardPersenter extends BasePresenter<RewardContract.Model,RewardContract.View> {

    @Inject
    List<RewardPunishmentInfoBean> mList;
    @Inject
    RewardListBean bean;

    @Inject
    public RewardPersenter(RewardContract.Model model, RewardContract.View rootView) {
        super(model, rootView);
    }

    public void queryRewardList(){
        DisposableObserver d = new OnSuccessAndFaultSub<RewardInfoBean>(new OnSuccessAndFaultListener<RewardInfoBean>() {
            @Override
            public void onSuccess(RewardInfoBean rewardInfoBean) {
                if (rewardInfoBean != null && rewardInfoBean.getRows() != null){
                    mList = rewardInfoBean.getRows();
                    queryList();
                }
            }

            @Override
            public void onFault(String errorMsg) {
                ToastUtils.showShort(errorMsg);
            }
        },RewardInfoBean.class);

        HttpUtils.getInstance().toSubscribe(mModel.queryRewardList(),
                d);

        mCompositeDisposable.add(d);
    }

    public void queryList(){
        DisposableObserver d = new OnSuccessAndFaultSub<RewardNewInfoBean>(new OnSuccessAndFaultListener<RewardNewInfoBean>() {
            @Override
            public void onSuccess(RewardNewInfoBean rewardNewInfoBean) {
                if (rewardNewInfoBean != null && rewardNewInfoBean.getDataGrid() != null && rewardNewInfoBean.getDataGrid().getRows() != null){
                    bean.setNewList(rewardNewInfoBean.getDataGrid().getRows());
                    bean.setList(mList);
                    mRootView.initRewardNewList(bean);
                }
            }

            @Override
            public void onFault(String errorMsg) {
                ToastUtils.showShort(errorMsg);
            }
        },RewardNewInfoBean.class);

        HttpUtils.getInstance().toSubscribe(mModel.queryRewardNewList(),
                d);

        mCompositeDisposable.add(d);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mList = null;
    }
}

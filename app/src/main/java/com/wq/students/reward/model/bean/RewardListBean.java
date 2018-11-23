package com.wq.students.reward.model.bean;

import java.util.List;

/**
 * 项目名称：    com.wq.students.reward.model.bean
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/21
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/21
 * 修改备注：
 */
public class RewardListBean {

    /** 最新数据 */
    private List<RewardPunishmentInfoBean> newList;

    /** 历史数据 */
    private List<RewardPunishmentInfoBean> list;

    public List<RewardPunishmentInfoBean> getNewList() {
        return newList;
    }

    public void setNewList(List<RewardPunishmentInfoBean> newList) {
        this.newList = newList;
    }

    public List<RewardPunishmentInfoBean> getList() {
        return list;
    }

    public void setList(List<RewardPunishmentInfoBean> list) {
        this.list = list;
    }
}

package com.wq.students.reward.model.bean;

/**
 * 项目名称：    com.wq.students.reward.model.bean
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/21
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/21
 * 修改备注：
 */
public class RewardNewInfoBean {

    /** 奖的总数 */
    private String prizeCount;

    /** 罚的总数 */
    private String penaltyCount;

    /** 数据 */
    private RewardInfoBean dataGrid;

    public String getPrizeCount() {
        return prizeCount;
    }

    public void setPrizeCount(String prizeCount) {
        this.prizeCount = prizeCount;
    }

    public String getPenaltyCount() {
        return penaltyCount;
    }

    public void setPenaltyCount(String penaltyCount) {
        this.penaltyCount = penaltyCount;
    }

    public RewardInfoBean getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(RewardInfoBean dataGrid) {
        this.dataGrid = dataGrid;
    }
}

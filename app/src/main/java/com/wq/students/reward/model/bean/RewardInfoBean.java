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
public class RewardInfoBean {

    /** 数据 */
    private List<RewardPunishmentInfoBean> rows;

    /** 总数 */
    private String total;

    public List<RewardPunishmentInfoBean> getRows() {
        return rows;
    }

    public void setRows(List<RewardPunishmentInfoBean> rows) {
        this.rows = rows;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}

package com.wq.students.reward.model.bean;

/**
 * 奖罚信息实体类
 * 类描述：
 * 开发人员JC
 * 版本号：1.0
 * 开发公司：重庆菀晴科技有限公司
 */
public class RewardPunishmentInfoBean {

	private String id;
    private String student_id;//学籍id
    private String reward_punishment_type;//奖罚类型
    private String pp_date;//时间
    private String pp_reason;//奖罚原因
    private String pp_detailed;//奖罚明细
    private String pp_sysdate;//系统时间
    private String cardid;//身份证号码
    /***************以下是非数据库字段*****************/
    private String name;
    private String student_no;//学号
    private String classid;//所在班级名称

    public String getStudent_no() {
        return student_no;
    }

    public void setStudent_no(String student_no) {
        this.student_no = student_no;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RewardPunishmentInfoBean(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getReward_punishment_type() {
        return reward_punishment_type;
    }

    public void setReward_punishment_type(String reward_punishment_type) {
        this.reward_punishment_type = reward_punishment_type;
    }

    public String getPp_date() {
        return pp_date;
    }

    public void setPp_date(String pp_date) {
        this.pp_date = pp_date;
    }

    public String getPp_reason() {
        return pp_reason;
    }

    public void setPp_reason(String pp_reason) {
        this.pp_reason = pp_reason;
    }

    public String getPp_detailed() {
        return pp_detailed;
    }

    public void setPp_detailed(String pp_detailed) {
        this.pp_detailed = pp_detailed;
    }

    public String getPp_sysdate() {
        return pp_sysdate;
    }

    public void setPp_sysdate(String pp_sysdate) {
        this.pp_sysdate = pp_sysdate;
    }

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
}

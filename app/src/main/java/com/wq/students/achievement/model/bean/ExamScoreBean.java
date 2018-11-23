package com.wq.students.achievement.model.bean;

/**
 * 考试得分情况ExamScore
 * 类描述：
 * 开发人员JC
 * 版本号：1.0
 * 开发公司：重庆菀晴科技有限公司
 */

public class ExamScoreBean {

	private String id;//ID
    private String info_id;//考试信息ID
    private String class_id;//班级id
    private String course_id;//科目ID
    private String course_name;//科目名称
    private String userid;//考生ID
    private String username;//考生名称
    private String score;//得分
    private String flag;//是否草稿（1：草稿 2：不是草稿）
    private String addtime;//增加时间
    private String isresit;//是否补考
    private String rank;//优良中差
    private String isrank;//是否优良中差
    private String ranking;//当前排名
    private String oldranking;//旧排名

    /**以下非数据库字段**/
    private String exam_name;//考试名称
    
    public String getIsresit() {
        return isresit;
    }

    public void setIsresit(String isresit) {
        this.isresit = isresit;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getIsrank() {
        return isrank;
    }

    public void setIsrank(String isrank) {
        this.isrank = isrank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public String getOldranking() {
		return oldranking;
	}

	public void setOldranking(String oldranking) {
		this.oldranking = oldranking;
	}

	public String getExam_name() {
		return exam_name;
	}

	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}
}

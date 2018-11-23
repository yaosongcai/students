package com.wq.students.timetable.model.bean;

/**
 * 班级管理实体类
 * 类描述：
 * 开发人员JC
 * 版本号：1.0
 * 开发公司：重庆菀晴科技有限公司
 */
public class StudentClassManagementBean {
	private String id;//ID
    private String grade_id;//所在年级id
    private String grade_name;//所在年级名称
    private String classCode;//班级编号
    private String headMasterId;//班主任ID
    private String headMasterName;//班主任名字
    private String classAddress;//教室位置
    private String studentCount;//学生数量
    private String addtime;//新增日期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(String grade_id) {
        this.grade_id = grade_id;
    }

    public String getGrade_name() {
        return grade_name;
    }

    public void setGrade_name(String grade_name) {
        this.grade_name = grade_name;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getHeadMasterId() {
        return headMasterId;
    }

    public void setHeadMasterId(String headMasterId) {
        this.headMasterId = headMasterId;
    }

    public String getHeadMasterName() {
        return headMasterName;
    }

    public void setHeadMasterName(String headMasterName) {
        this.headMasterName = headMasterName;
    }

    public String getClassAddress() {
        return classAddress;
    }

    public void setClassAddress(String classAddress) {
        this.classAddress = classAddress;
    }

    public String getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(String studentCount) {
        this.studentCount = studentCount;
    }

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
}

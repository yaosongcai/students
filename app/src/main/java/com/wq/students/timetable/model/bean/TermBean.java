package com.wq.students.timetable.model.bean;

/**
 * 学期实体类
 * 类描述：
 * 开发人员JC
 * 版本号：1.0
 * 开发公司：重庆菀晴科技有限公司
 */
public class TermBean {

	private String id;
    private String titles;//学期名称
    private String isCurrent;//1当前学期，0默认值
    private String starttime;//开始时间
    private String endtime;//1结束时间
    private boolean isClick;//是否点击


    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(String isCurrent) {
        this.isCurrent = isCurrent;
    }

}

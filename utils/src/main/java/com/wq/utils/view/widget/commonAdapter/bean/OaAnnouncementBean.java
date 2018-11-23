package com.wq.utils.view.widget.commonAdapter.bean;

/**
 * 类描述：公告信息表
 * 开发人员 LPC
 * 版本号：1.0
 * 开发公司：重庆菀晴科技有限公司
 */
public class OaAnnouncementBean {

    private String announcement_id;//
    private String announcement_title;//公告标题
    private String status_id;//公告状态（草稿、正式发布）0是发布 1是草稿
    private String classify_id;//公告类别ID
    private String create_userid;//创建人ID
    private String create_time;//创建时间
    private String release_time;//发布时间
    private String announcement_open_status;//公告开启状态（1开启、0终止）
    private String message_status;//消息提醒状态（0不提醒1提醒）

    /*以下非数据库字段*/
    private String content_value;//正文内容
    private String num;//浏览量
    private String user_name;//创建人

    public String getAnnouncement_id() {
        return announcement_id;
    }

    public void setAnnouncement_id(String announcement_id) {
        this.announcement_id = announcement_id;
    }

    public String getAnnouncement_title() {
        return announcement_title;
    }

    public void setAnnouncement_title(String announcement_title) {
        this.announcement_title = announcement_title;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(String classify_id) {
        this.classify_id = classify_id;
    }

    public String getCreate_userid() {
        return create_userid;
    }

    public void setCreate_userid(String create_userid) {
        this.create_userid = create_userid;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public String getAnnouncement_open_status() {
        return announcement_open_status;
    }

    public void setAnnouncement_open_status(String announcement_open_status) {
        this.announcement_open_status = announcement_open_status;
    }

    public String getMessage_status() {
        return message_status;
    }

    public void setMessage_status(String message_status) {
        this.message_status = message_status;
    }

    public String getContent_value() {
        return content_value;
    }

    public void setContent_value(String content_value) {
        this.content_value = content_value;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}

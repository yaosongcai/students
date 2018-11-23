package com.wq.utils.view.widget.commonAdapter.bean;


public class UserInfo {

    /**
     * RETURN: [BOK:成功
     ERR:失败]
     MESSAGE:提示信息
     DATA:[
     userid :用户id
     user_name:用户名字
     gender:用户性别(1男2女)
     phone:电话
     addr:地址
     company:公司
     app_url:图片地址
     ]
     */

    private String MESSAGE;
    private String RETURN;
    private DATABean DATA;

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getRETURN() {
        return RETURN;
    }

    public void setRETURN(String RETURN) {
        this.RETURN = RETURN;
    }

    public DATABean getDATA() {
        return DATA;
    }

    public void setDATA(DATABean DATA) {
        this.DATA = DATA;
    }

    public static class DATABean{

        private String userid;

        private String user_name;

        private String gender;

        private String phone;

        private String addr;

        private String company;

        private String app_url;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getApp_url() {
            return app_url;
        }

        public void setApp_url(String app_url) {
            this.app_url = app_url;
        }
    }
}

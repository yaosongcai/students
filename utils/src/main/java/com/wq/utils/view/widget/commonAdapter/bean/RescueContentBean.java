package com.wq.utils.view.widget.commonAdapter.bean;

/**
 * 项目名称： SmartCampus
 * 类描述     首页各模块入口bean
 * 创建人：   Yaosongcai
 * 创建时间： 2018\5\10 0010 9:40
 * 修改人：   Yaosongcai
 * 修改时间： Yaosongcai or 2018\5\10 0010 9:40
 * 修改备注：
 */

public class RescueContentBean {
    private int icon;
    private String title;
    private Class aClass;
    private int type;

    public RescueContentBean(int icon, String title, Class aClass,int type) {
        this.icon = icon;
        this.title = title;
        this.aClass = aClass;
        this.type = type;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

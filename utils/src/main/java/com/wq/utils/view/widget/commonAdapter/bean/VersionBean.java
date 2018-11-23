package com.wq.utils.view.widget.commonAdapter.bean;


public class VersionBean {

    /**
     * MESSAGE : 加载成功
     * RETURN : BOK
     * DATA : {"total":1,"remark":"更新第二个版本","url":"http://localhost:8080/WQWL_V2.apk"}
     */

    private int total;
    private String remark;
    private String url;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

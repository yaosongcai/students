package com.wq.students.base.utils.baseEntity;

/**
 * 项目名称：    com.wq.students.base.utils.baseEntity
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/9
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/9
 * 修改备注：
 */
public class ResultBean<T> {

    private String MESSAGE;

    private String RETURN;

    private T DATA;

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

    public T getDATA() {
        return DATA;
    }

    public void setDATA(T DATA) {
        this.DATA = DATA;
    }
}

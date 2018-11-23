package com.wq.students.timetable.ui.widget;

import java.util.List;

/**
 * 项目名称：    com.wq.students.timetable.ui.widget
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/16
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/16
 * 修改备注：
 */
public class ChildBean {

    //是否合并
    public boolean isMerge;

    //内容
    public String content;

    //字宽度
    public int StrWidth;

    //基准线
    public float baseLine;

    //一行高度
    public int height;

    //当前高度
    public int currentHeight;

    //具体的值
    public List<Values> values;
}

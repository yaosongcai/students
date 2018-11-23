package com.wq.students.timetable.ui.widget;

import java.util.List;

/**
 * 项目名称：    com.wq.students.timetable.ui.widget
 * 类描述        列信息
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/15
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/15
 * 修改备注：
 */
public class Colum {

    //是否是顶部table
    public boolean isTopTable;

    //一行高度
    public int height;

    //当前高度
    public int currentHeight;

    //一个table高度
    public int  columHeight;

    //是否合并
    public boolean isMerge;

    //合并内容
    public Values mergeContent;

    //左边table
    public Values leftTable;

    //内容
    public List<ChildBean> conotent;

    //整个tab高度
    public int tabHeight;

    //左table宽度
//    public int leftWidth;

}

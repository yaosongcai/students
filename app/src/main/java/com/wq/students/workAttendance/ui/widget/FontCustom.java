package com.wq.students.workAttendance.ui.widget;

import android.content.Context;
import android.graphics.Typeface;

/**
 * 项目名称：    com.wq.students.workAttendance.ui.widget
 * 类描述        字体工具
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/25
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/25
 * 修改备注：
 */
public class FontCustom {

    private static final String fontUrl = "cfst.ttf";

    private static Typeface tf;

    /**
     *
     * 设置字体
     * @author ysc
     * @time 2018/10/25 11:50
     */
    public static Typeface setFont(Context context){
        if (tf == null){
            tf = Typeface.createFromAsset(context.getAssets(),fontUrl);
        }
        return tf;
    }
}

package com.wq.students.workAttendance.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 项目名称：    com.wq.students.workAttendance.ui.widget
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/25
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/25
 * 修改备注：
 */
public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        this(context,null);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setTypeface(FontCustom.setFont(context));
    }
}

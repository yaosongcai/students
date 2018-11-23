package com.wq.utils.auto;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

;

/**
 * 项目名称：
 * 类描述
 *  <pre>
 *      LayoutActivity自动适配各手机屏幕继承类
 *  </pre>
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/9/19
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/9/19
 * 修改备注：
 */
public class AutoLayoutActivity extends AppCompatActivity
{
    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs)
    {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT))
        {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT))
        {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT))
        {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }




}

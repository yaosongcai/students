package com.wq.utils.view.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.wq.utils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：    com.wq.utils.view.widget
 * 类描述
 * <pre>
 *     自定义底部页卡
 *     链式
 * </pre>
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/9/28
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/9/28
 * 修改备注：    2018-10-19 完善注释
 */
public class HjmBottomTab extends RelativeLayout {

    private int fontSize = 14;
    private int imgWidht = 0, imgHeight = 0;
    private int fontImgPadding = 0;//图片和文字的间距(就算设成0了还是分隔太开了,设成负数就好了)
    private int paddingTop = 0, paddingBottom = 0;

    private int selectColor = Color.parseColor("#F1453B"), unSelectColor = Color.parseColor("#626262");

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> fonts = new ArrayList<>();
    private List<Drawable> imgs = new ArrayList<>();
    private List<Boolean> isSelect = new ArrayList<>();

    private Context context;
    private RelativeLayout mBottomTabLayout;
    private RadioGroup mRadioGroup;
    private OnTabChangeListener onTabChangeListener;

    public interface OnTabChangeListener {
        void onTabChangeListener(int position, String tabName);
    }

    /**
     * 设置页卡切换监听器
     * @param onTabChangeListener       监听器
     * @return
     */
    public HjmBottomTab setOnTabChangeListener(OnTabChangeListener onTabChangeListener) {
        this.onTabChangeListener = onTabChangeListener;
        return this;
    }

    /**
     * 设置页卡字体大小
     * @param fontSize     字体大小
     * @return
     */
    public HjmBottomTab setFontSize(int fontSize) {
        this.fontSize = fontSize <= 0 ? this.fontSize : fontSize;
        return this;
    }

    /**
     * 设置图片尺寸
     * @param imgWidht      图片宽度
     * @param imgHeight     图片高度
     * @return
     */
    public HjmBottomTab setImgSize(int imgWidht, int imgHeight) {
        this.imgWidht = imgWidht <= 0 ? this.imgWidht : dp2px(imgWidht);
        this.imgHeight = imgHeight <= 0 ? this.imgHeight : dp2px(imgHeight);
        return this;
    }

    /**
     * 设置字体图片的内边距
     * @param fontImgPadding    内边距
     * @return
     */
    public HjmBottomTab setFontImgPadding(int fontImgPadding) {
        this.fontImgPadding = fontImgPadding <= 0 ? dp2px(-3) : dp2px(fontImgPadding);
        return this;
    }

    /**
     * 设置顶部内边距
     * @param paddingTop
     * @return
     */
    public HjmBottomTab setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop <= 0 ? dp2px(2) : dp2px(paddingTop);
        return this;
    }

    /**
     * 设置底部内边距
     * @param paddingBottom
     * @return
     */
    public HjmBottomTab setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom <= 0 ? dp2px(5) : dp2px(paddingBottom);
        return this;
    }

    /**
     * 设置选中时的颜色与未选中时的颜色
     * @param selectColor       选中时的颜色
     * @param unSelectColor     为选中时的颜色
     * @return
     */
    public HjmBottomTab setSelectColor(int selectColor, int unSelectColor) {
        this.selectColor = selectColor;
        this.unSelectColor = unSelectColor;
        return this;
    }

    /**
     * 设置背景
     * @param color     背景颜色
     * @return
     */
    public HjmBottomTab setBackground(int color) {
        this.mRadioGroup.setBackgroundColor(color);
        return this;
    }

    /**
     * 初始化
     * @param context
     * @return
     */
    public HjmBottomTab initTab(Context context) {
        this.context = context;
        mBottomTabLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.hjm_bottomtab_layout, null);
        mRadioGroup = (RadioGroup) mBottomTabLayout.findViewById(R.id.hjm_bottomtab_rg);
        addView(mBottomTabLayout);
        return this;
    }

    /**
     * 添加tab
     * @param name        页卡名称
     * @param img         页卡图片
     * @param fragment    页卡绑定的fragment
     * @return
     */
    public HjmBottomTab addTab(String name, int img, Fragment fragment) {
        return addTab(name, img, fragment, false);
    }

    /**
     * 添加页卡
     * @param name                  页卡名称
     * @param imgId                 页卡图片
     * @param fragment              页卡绑定的fragment
     * @param isDefautSelect        是否默认选中
     * @return
     */
    public HjmBottomTab addTab(String name, int imgId, Fragment fragment, boolean isDefautSelect) {
        return addTab(name, ContextCompat.getDrawable(context, imgId), fragment, isDefautSelect);
    }

    /**
     * 添加页卡
     * @param name                  页卡名称
     * @param drawable              页卡图片
     * @param fragment              绑定的fragment
     * @param isDefautSelect        是否默认选中
     * @return
     */
    public HjmBottomTab addTab(String name, Drawable drawable, Fragment fragment, boolean isDefautSelect) {
        fonts.add(TextUtils.isEmpty(name) ? "TAB" : name);
        imgs.add(drawable);
        fragments.add(fragment);
        isSelect.add(isDefautSelect);
        return this;
    }

    /**
     * 构建
     */
    public void show() {
        for (int i = 0; i < fonts.size(); i++) {
            final int position = i;
            RadioButton radioButton = (RadioButton) LayoutInflater.from(context).inflate(R.layout.hjm_item_bottomtab, null);
            RadioGroup.LayoutParams params = null;
            params = new RadioGroup.LayoutParams(0, RadioGroup.LayoutParams.WRAP_CONTENT, 1f);
            radioButton.setLayoutParams(params);
            radioButton.setPadding(dp2px(10), paddingTop, dp2px(10), paddingBottom);

            radioButton.setText(fonts.get(i));
            radioButton.setTextSize(fontSize);

            int[][] states = new int[3][];
            states[0] = new int[]{android.R.attr.state_pressed};
            states[1] = new int[]{android.R.attr.state_checked};
            states[2] = new int[]{};
            int[] colors = new int[]{selectColor, selectColor, unSelectColor};//colorSelect跟states[0]对应，color跟states[2]对应，以此类推
            ColorStateList csl = new ColorStateList(states, colors);
            radioButton.setTextColor(csl);

            Drawable drawable = imgs.get(i);
            int[] colorsImg = new int[]{selectColor, selectColor, unSelectColor};//colorSelect跟states[0]对应，color跟states[2]对应，以此类推
            int[][] statesImg = new int[3][];
            statesImg[0] = new int[]{android.R.attr.state_pressed};
            statesImg[1] = new int[]{android.R.attr.state_checked};
            statesImg[2] = new int[]{};
            ColorStateList colorList = new ColorStateList(statesImg, colorsImg);
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(statesImg[0], drawable);//注意顺序
            stateListDrawable.addState(statesImg[1], drawable);
            stateListDrawable.addState(statesImg[2], drawable);
            Drawable.ConstantState state = stateListDrawable.getConstantState();
            drawable = DrawableCompat.wrap(state == null ? stateListDrawable : state.newDrawable()).mutate();
            DrawableCompat.setTintList(drawable, colorList);
            int width = imgWidht == 0 ? drawable.getMinimumWidth() : imgWidht;
            int height = imgHeight == 0 ? drawable.getMinimumHeight() : imgHeight;
            drawable.setBounds(0, 0, width, height);
            radioButton.setCompoundDrawables(null, drawable, null, null);
            radioButton.setCompoundDrawablePadding(fontImgPadding);

            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (onTabChangeListener != null) {
                            onTabChangeListener.onTabChangeListener(position, fonts.get(position));
                        }
                        bottomTabChange((FragmentActivity) context, position);
                    }
                }
            });

            mRadioGroup.addView(radioButton);
        }

        for (int i = 0; i < isSelect.size(); i++) {
            if (isSelect.get(i) == true) {
                ((RadioButton) mRadioGroup.getChildAt(i)).setChecked(true);
                break;
            }
        }
    }

    private void bottomTabChange(FragmentActivity fragmentActivity, int positon) {
        Fragment fragment = fragments.get(positon);
        FragmentManager manager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        List<Fragment> fragmentList = manager.getFragments();
        if (fragmentList != null) {
            if (fragmentList.contains(fragment)) {
                transaction.show(fragment);
            } else {
                transaction.add(R.id.hjm_bottomtab_fragment_vp, fragment);
            }
            for (Fragment fm : fragmentList) {
                if (!fm.equals(fragment)) {
                    transaction.hide(fm);
                }
            }
        } else {
            transaction.add(R.id.hjm_bottomtab_fragment_vp, fragment);
        }
        transaction.commit();
    }

    //=======================构造方法START========================================
    public HjmBottomTab(Context context) {
        super(context);
    }

    public HjmBottomTab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HjmBottomTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public HjmBottomTab(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    //=======================构造方法END==========================================

    //=========================工具类START========================================
    private int dp2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    //=========================工具类END===========================================
}

package com.wq.utils.view.utils;

import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import org.xml.sax.XMLReader;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * 项目名称： SmartCampus
 * 类描述     {@link Html#fromHtml(String, int)}不支持html的size标签 自定义标签动态改变text的size
 * 创建人：   Yaosongcai
 * 创建时间： 2018\5\14 0014 9:45
 * 修改人：   Yaosongcai
 * 修改时间： Yaosongcai or 2018\5\14 0014 9:45
 * 修改备注：
 */

public class HtmlTagHandler implements Html.TagHandler {
    // 自定义标签名称
    private String tagName;
    // 标签开始索引
    private int startIndex = 0;
    // 标签结束索引
    private int endIndex = 0;
    // 存放标签所有属性键值对
    final HashMap<String, String> attributes = new HashMap<>();

    /**
     * 构造器
     * @param tagName   html自定义标签
     */
    public HtmlTagHandler(String tagName) {
        this.tagName = tagName;
    }

    /**
     *
     * @param opening   字体大小是否不一致
     * @param tag       定义标签tag
     * @param output
     * @param xmlReader
     */
    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        // 判断是否是当前需要的tag
        if (tag.equalsIgnoreCase(tagName)) {
            // 解析所有属性值
            parseAttributes(xmlReader);
            if (opening) {
                startHandleTag(tag, output, xmlReader);
            } else {
                endEndHandleTag(tag, output, xmlReader);
            }
        }
    }

    private void startHandleTag(String tag, Editable output, XMLReader xmlReader) {
        startIndex = output.length();
    }

    private void endEndHandleTag(String tag, Editable output, XMLReader xmlReader) {
        endIndex = output.length(); // 获取对应的属性值
        String color = attributes.get("color");
        String size = attributes.get("size");
        size = size.split("px")[0];
        // 设置颜色
        if (!TextUtils.isEmpty(color)) {
            output.setSpan(new ForegroundColorSpan(Color.parseColor(color)), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        // 设置字体大小
        if (!TextUtils.isEmpty(size)) {
            output.setSpan(new AbsoluteSizeSpan(Integer.parseInt(size)), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    /**
     * 解析所有属性值 * * @param xmlReader
     */
    private void parseAttributes(final XMLReader xmlReader) {
        try {
            Field elementField = xmlReader.getClass().getDeclaredField("theNewElement");
            elementField.setAccessible(true);
            Object element = elementField.get(xmlReader);
            Field attsField = element.getClass().getDeclaredField("theAtts");
            attsField.setAccessible(true);
            Object atts = attsField.get(element);
            Field dataField = atts.getClass().getDeclaredField("data");
            dataField.setAccessible(true);
            String[] data = (String[]) dataField.get(atts);
            Field lengthField = atts.getClass().getDeclaredField("length");
            lengthField.setAccessible(true);
            int len = (Integer) lengthField.get(atts);
            for (int i = 0; i < len; i++) {
                attributes.put(data[i * 5 + 1], data[i * 5 + 4]);
            }
        } catch (Exception e) {
        }
    }
}

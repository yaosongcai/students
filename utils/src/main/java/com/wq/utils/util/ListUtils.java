package com.wq.utils.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 项目名称：    com.wq.smartcampus.utils
 * 类描述        list集合工具类
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/9/6
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/9/6
 * 修改备注：
 */
public class ListUtils {

    /**
     *
     * 通过HashSet踢除重复元素
     * @author ysc
     * @time 2018/9/6 15:37
     */
    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    /**
     *
     * 删除重复数据  保持顺序
     * @author ysc
     * @time 2018/9/6 15:39
     */
    public static void removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        System.out.println( " remove duplicate " + list);
    }
}

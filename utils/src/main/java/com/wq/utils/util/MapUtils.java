package com.wq.utils.util;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 项目名称：    com.wq.community.utils
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/6/8
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/6/8
 * 修改备注：
 */
public class MapUtils {

    /**
     * Json 转成 Map<>
     *
     * @param jsonStr
     * @return
     */
    public static Map<String, String> getMapForJson(String jsonStr) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonStr);

            Iterator<String> keyIter = jsonObject.keys();
            String key;
            Object value;
            Map<String, String> valueMap = new HashMap<String, String>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.get(key);
                if (value instanceof String)
                    valueMap.put(key, (String) value);
                else
                    valueMap.put(key,String.valueOf(value));
            }
            return valueMap;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
}

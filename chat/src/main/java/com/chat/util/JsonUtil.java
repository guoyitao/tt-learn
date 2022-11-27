package com.chat.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @description://json工具类 把字符串转成对象  把对象转成字符串 用来解析对象
 * @author: Luck_chen
 * @date: 2022/11/8 20:59
 * @Version 1.0.0.0
 */
public class JsonUtil {
    //把对象转成字符串
    public static String writeObjectAsString(Object obj){
        return JSONObject.toJSONString(obj);
    }
    //用来解析对象
    public static <T> T readToObject(String obj,Class<T> clazz){
        return JSONObject.parseObject(obj,clazz);
    }
}

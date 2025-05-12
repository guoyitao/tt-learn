package yaxintool;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;

import java.util.List;

public class RequestToJsonData {
    public static void main(String[] args) {
         String data = "REQUEST_TYPE=closeJinkOrder,OUT_ORDER_NO=1111231,REASON=不想要了";

        // 将字符串按逗号分割
        List<String> split = StrUtil.split(data, ',');

        // 创建一个JSONObject对象
        JSONObject json = new JSONObject();

        // 遍历每个键值对
        for (String pair : split) {
            // 将键值对按等号分割
            List<String> keyValue = StrUtil.split(pair, '=');
            if (keyValue.size() == 2) {
                // 将键值对放入JSONObject
                json.put(keyValue.get(0), keyValue.get(1));
            }
        }

        // 输出JSON对象
        System.out.println(json.toString());



    }

}

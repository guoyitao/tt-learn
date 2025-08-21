package yaxintool;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class JsonToRequestData {
    public static void main(String[] args) {


        // 示例 JSON 字符串
        String jsonString = "{\"RULE_TYPE\":\"R2_Y_BACK_PACKAGE\",\"START_DATE\":\"20250615\",\"USER_ID\":\"2919101022445083\",\"IMEI\":\"86592007913969\",\"IMEI_OTH\":\"86592007913968\",\"ACCEPT_DATE\":\"2025-06-12\",\"PACKAGE_ID\":\"96087801\",\"TYPE_ID\":\"2\",\"STAFF_ID\":\"AALSQ268\",\"DEPART_ID\":\"Y8075\",\"TERM_IF_USE\":\"1\",\"TERM_USER_IF_OK_Z\":\"1\",\"TERM_USER_IF_OK_K\":\"1\",\"USER_IF_OK_Z\":\"1\",\"USER_IF_OK_K\":\"1\",\"RSRV_TAG1\":\"1\",\"RSRV_NUM\":\"0\",\"RSRV_STR3\":\"2506160911651937\",\"IN_DATE\":\"2025-06-16 09:11:13\",\"TERM_IF_USE_3DAYS\":\"1\",\"USER_IF_OK_k_7\":\"0\",\"ROWID\":\"AAqcXeAJ3AADQj1AAa\"}";

//        String jsonString = "{\"RULE_TYPE\":\"R2_Y_BACK_PACKAGE\",\"START_DATE\":\"20250513\",\"USER_ID\":\"1007103012168401\",\"IMEI\":\"86337707306678\",\"IMEI_OTH\":\"86337707306678\",\"ACCEPT_DATE\":\"2025-05-10\",\"PACKAGE_ID\":\"96087807\",\"TYPE_ID\":\"2\",\"STAFF_ID\":\"DXQ40795\",\"DEPART_ID\":\"19282\",\"CITY_CODE\":\"D0WX\",\"TERM_IF_USE\":\"1\",\"TERM_USER_IF_OK_Z\":\"0\",\"TERM_USER_IF_OK_K\":\"1\",\"USER_IF_OK_Z\":\"0\",\"USER_IF_OK_K\":\"1\",\"RSRV_TAG1\":\"1\",\"RSRV_NUM\":\"0\",\"RSRV_STR3\":\"2505140910348463\",\"IN_DATE\":\"2025-05-14 09:10:11\",\"TERM_IF_USE_3DAYS\":\"1\",\"USER_IF_OK_k_7\":\"0\",\"ROWID\":\"AAqcXeAJ3AACVBNAAw\"}";

        JSONObject jsonObject = JSONUtil.parseObj(jsonString);

        // 调用方法转换 JSON 到目标格式
        String result = convertJsonToParams(jsonObject);

        // 输出结果
        System.out.println(result);  // 输出: a=b,c=ssda
    }

    /**
     * 将 JSONObject 转换为 a=b,c=d 格式的字符串
     * @param jsonObject 需要转换的 JSON 对象
     * @return 转换后的字符串
     */
    public static String convertJsonToParams(JSONObject jsonObject) {
        StringBuilder params = new StringBuilder();
        boolean first = true;

        for (String key : jsonObject.keySet()) {
            if (!first) {
                params.append(",");
            }
            String str = jsonObject.getStr(key);
            if (str == null){
                params.append(key).append("=");
            }else{
                params.append(key).append("=").append(str);
            }

            first = false;
        }

        return params.toString();
    }


}

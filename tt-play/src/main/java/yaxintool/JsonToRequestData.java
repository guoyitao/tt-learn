package yaxintool;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class JsonToRequestData {
    public static void main(String[] args) {


        // 示例 JSON 字符串
        String jsonString = "{\"RULE_TYPE\":\"R2_Y_BACK_PACKAGE\",\"START_DATE\":20250310,\"USER_ID\":1713112528151102,\"IMEI\":\"89564345323456\",\"IMEI_OTH\":\"86869207099968\",\"ACCEPT_DATE\":\"2025-04-09\",\"PACKAGE_ID\":\"96065858\",\"TYPE_ID\":2,\"STAFF_ID\":\"CCH20535\",\"DEPART_ID\":\"A76EE\",\"CITY_CODE\":\"C0CH\",\"TERM_IF_USE\":1,\"TERM_USER_OK_Z\":null,\"TERM_USER_OK_K\":0,\"TERM_USER_IF_OK_Z\":0,\"TERM_USER_IF_OK_K\":1,\"USER_IF_OK_Z\":0,\"USER_IF_OK_K\":1,\"OPERATOR_TYPE\":null,\"REMARKS\":null,\"RSRV_TAG1\":\"1\",\"RSRV_TAG2\":null,\"RSRV_NUM1\":0,\"RSRV_DATE1\":null,\"RSRV_DATE2\":null,\"RSRV_STR1\":null,\"RSRV_STR2\":null,\"RSRV_STR3\":\"2504301701196782\",\"RSRV_STR4\":\"\",\"RSRV_STR5\":null,\"IN_DATE\":\"2025-03-11 09:11:48\",\"TERM_IF_USE_3DAYS\":\"0\",\"USER_IF_OK_K_7\":\"0\"}";

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

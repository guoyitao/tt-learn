package yaxintool;


import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentRequestGenerator {

    public static JSONObject generatePaymentRequest(Map<String, String> param) {
        // 创建 contentInput 和 contentMap
        JSONObject contentInput = new JSONObject();
        JSONObject contentMap = new JSONObject();

        // 设置 contentInput 参数
        contentInput.put("out_order_no", param.get("QUERY_RECORD_ID")); // 商户平台订单编号
        double queryPrice = Double.parseDouble(param.get("QUERY_PRICE"));
        int totalAmount = (int) (queryPrice * 100);
        contentInput.put("total_amount", totalAmount); // 订单总金额，单位为分
        contentInput.put("pay_type", param.get("PAY_TYPE")); // 支付类型
        contentInput.put("auth_code", param.get("AUTH_CODE")); // 支付凭证串
        contentInput.put("ledger_flag", MapUtil.getStr(param, "LEDGER_FLAG", "0")); // 分账标志
        contentInput.put("purchase_mode", MapUtil.getStr(param, "PURCHASE_MODE", "0")); // 购买模式
        contentInput.put("settle_type", MapUtil.getStr(param, "SETTLE_TYPE", "0")); // 结算类型

        // 设置 contentMap 参数
        contentMap.put("currency", "CNY"); // 应付货币单位，默认 CNY
        contentMap.put("institution", param.get("INSTITUTION")); // 支付机构

        // 创建 orderDetailMap
        JSONObject orderDetailMap = new JSONObject();
        orderDetailMap.put("subject", param.get("RES_SKU_NAME")); // 描述子订单商品的信息
        orderDetailMap.put("amount", totalAmount); // 子订单应付金额，单位为分
        orderDetailMap.put("service_type", "SXFA"); // 外部商户类型

        // 创建 goodsDetailList 和 goodsDetailMap
        List<JSONObject> goodsDetailList = new ArrayList<>();
        JSONObject goodsDetailMap = new JSONObject();
        goodsDetailMap.put("goods_id", param.get("IMEI")); // 商品的编号
        goodsDetailMap.put("goods_name", param.get("RES_SKU_NAME")); // 商品名称
        goodsDetailMap.put("goods_category", param.get("RES_SKU_ID")); // 商品类型
        goodsDetailMap.put("quantity", 1); // 商品数量
        goodsDetailMap.put("price", totalAmount); // 商品单价，精度到分。单位为分
        goodsDetailMap.put("discount", 0); // 商品优惠，精度到分。单位为分,要求为负值
        goodsDetailList.add(goodsDetailMap);

        // 将 goodsDetailList 添加到 orderDetailMap
        orderDetailMap.put("goods_detail", goodsDetailList);

        // 将 orderDetailMap 添加到 contentInput
        contentInput.put("order_detail", orderDetailMap);

        // 创建最终的请求参数
        JSONObject requestParams = new JSONObject();
        requestParams.put("content_input", contentInput);
        requestParams.put("content_map", contentMap);

        return requestParams;
    }

    public static void main(String[] args) {
        Map<String, String> param = new HashMap<>();
        param.put("QUERY_RECORD_ID", "1234567890");
        param.put("QUERY_PRICE", "123.45");
        param.put("INSTITUTION", "ALI");
        param.put("PAY_TYPE", "ALI");
        param.put("AUTH_CODE", "12345678901234567890");
        param.put("LEDGER_FLAG", "0");
        param.put("PURCHASE_MODE", "0");
        param.put("SETTLE_TYPE", "0");
        param.put("RES_SKU_NAME", "商品名称");
        param.put("IMEI", "123456789012345");
        param.put("RES_SKU_ID", "1234567890");
        System.out.println(JSONUtil.toJsonStr(param));
        JSONObject requestParams = generatePaymentRequest(param);
        System.out.println(requestParams.toString());
    }
}



package yaxintool;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder
//@ApiModel(value = "终端一体化", description = "请求数据")
public class SingleSaleInfoDTO {

    private String imei;

    private String newPrice;

    private String downPrice;

    private String oldPrice;

    private String discntPrice;

    private String finallyPrice;

    private String productId;

    private String packageId;

    private String staffId;

    private String salePrice;

    private String cumuShowInfo;//店员编码

    //票据客户名称
    private String custName;

    private String ghfmc;//发票抬头

    private String ghfNsrsbh;//纳税人识别号

    private String saleType;

    private String resSkuName;//终端型号

    private String  materialCode;//物料编码

    private String  secondImei;//终端副串码

    private String hasPayPriv;//打印票据权限

    private String supplierNo;//厂家编码

    private String maxSalePrice;

    private String minSalePrice;
    //成本比例
    private String costRatio;

    private String isSaleSingle;

    private String orderId;

    private String recordId;
    //顺差让利销售价格
    private String salePriceSC;
    //客户经理信息
    private String staffName;
    //店员信息
    private String clerkCode;
    private String clerkName;
    //备注
    private String remarks;
    //号码1
    private String cardoNumber;
    //号码2
    private String cardtNumber;
    //是否开具电子发票
    private String staticCode;
    //副终端串码
    private String  falsesecondImei;
    //4G终端串号
    private String thirdImei;
    //供货平台
    private String supplierName;
    //预付比例
    private String advanceRatio;
    //
    private String modelName;
    //预订单号
    private String preOrderId;
    //预订单手机号
    private String preSerialNumber;
    //支付中心
    private String payType;
    //支付标识
    private String paySuccTag;
    //
    private String serialNumber;
    private String accessNumber;
    private String queryRecordId;
    //权益营销包
    private String rightsPackageId;
    // 以旧换新手机号
    private String oldPhoneNumber;
    // 以旧换新旧机订单
    private String oldOrderId;
    // 以旧换新手机号
    private String largePackageId;
    // 以旧换新旧机订单
    private String largeProductId;
    //促销金额
    private String tempProtionFee;
    //担保合约唯一流水
    private String guaranteeSeq;

    //是否开启检查 检查iemi正在被操作
    private Boolean checkImeiIsInProcessing;

    //1非统一收银 0统一收银
    private String nonUnifiedPayment;

    //0非国补  1国补
    private String hasGuobu;

    //国补开关0关 1开
    private String termGuobuEnable;

    //国补金额
    private String guobuPrice;

    //国补云闪付单号
    private String gbYsfOrderId;

    private String helloPrice;
}

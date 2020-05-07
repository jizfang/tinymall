package com.example.tinymall.core.system;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SystemConfig
 * @Description
 * @Author jzf
 * @Date 2020-4-9 17:13
 */
public class SystemConfig {
    // 小程序相关配置
    public final static String TINYMALL_WX_INDEX_NEW = "TINYMALL_wx_index_new";
    public final static String TINYMALL_WX_INDEX_HOT = "TINYMALL_wx_index_hot";
    public final static String TINYMALL_WX_INDEX_BRAND = "TINYMALL_wx_index_brand";
    public final static String TINYMALL_WX_INDEX_TOPIC = "TINYMALL_wx_index_topic";
    public final static String TINYMALL_WX_INDEX_CATLOG_LIST = "TINYMALL_wx_catlog_list";
    public final static String TINYMALL_WX_INDEX_CATLOG_GOODS = "TINYMALL_wx_catlog_goods";
    public final static String TINYMALL_WX_SHARE = "TINYMALL_wx_share";
    // 运费相关配置
    public final static String TINYMALL_EXPRESS_FREIGHT_VALUE = "TINYMALL_express_freight_value";
    public final static String TINYMALL_EXPRESS_FREIGHT_MIN = "TINYMALL_express_freight_min";
    // 订单相关配置
    public final static String TINYMALL_ORDER_UNPAID = "TINYMALL_order_unpaid";
    public final static String TINYMALL_ORDER_UNCONFIRM = "TINYMALL_order_unconfirm";
    public final static String TINYMALL_ORDER_COMMENT = "TINYMALL_order_comment";
    // 商场相关配置
    public final static String TINYMALL_MALL_NAME = "TINYMALL_mall_name";
    public final static String TINYMALL_MALL_ADDRESS = "TINYMALL_mall_address";
    public final static String TINYMALL_MALL_PHONE = "TINYMALL_mall_phone";
    public final static String TINYMALL_MALL_QQ = "TINYMALL_mall_qq";
    public final static String TINYMALL_MALL_LONGITUDE = "TINYMALL_mall_longitude";
    public final static String TINYMALL_MALL_Latitude = "TINYMALL_mall_latitude";

    //所有的配置均保存在该 HashMap 中
    private static Map<String, String> SYSTEM_CONFIGS = new HashMap<>();

    private static String getConfig(String keyName) {
        return SYSTEM_CONFIGS.get(keyName);
    }

    private static Integer getConfigInt(String keyName) {
        return Integer.parseInt(SYSTEM_CONFIGS.get(keyName));
    }

    private static Boolean getConfigBoolean(String keyName) {
        return Boolean.valueOf(SYSTEM_CONFIGS.get(keyName));
    }

    private static BigDecimal getConfigBigDec(String keyName) {
        return new BigDecimal(SYSTEM_CONFIGS.get(keyName));
    }

    public static Integer getNewLimit() {
        return getConfigInt(TINYMALL_WX_INDEX_NEW);
    }

    public static Integer getHotLimit() {
        return getConfigInt(TINYMALL_WX_INDEX_HOT);
    }

    public static Integer getBrandLimit() {
        return getConfigInt(TINYMALL_WX_INDEX_BRAND);
    }

    public static Integer getTopicLimit() {
        return getConfigInt(TINYMALL_WX_INDEX_TOPIC);
    }

    public static Integer getCatlogListLimit() {
        return getConfigInt(TINYMALL_WX_INDEX_CATLOG_LIST);
    }

    public static Integer getCatlogMoreLimit() {
        return getConfigInt(TINYMALL_WX_INDEX_CATLOG_GOODS);
    }

    public static boolean isAutoCreateShareImage() {
        return getConfigBoolean(TINYMALL_WX_SHARE);
    }

    public static BigDecimal getFreight() {
        return getConfigBigDec(TINYMALL_EXPRESS_FREIGHT_VALUE);
    }

    public static BigDecimal getFreightLimit() {
        return getConfigBigDec(TINYMALL_EXPRESS_FREIGHT_MIN);
    }

    public static Integer getOrderUnpaid() {
        return getConfigInt(TINYMALL_ORDER_UNPAID);
    }

    public static Integer getOrderUnconfirm() {
        return getConfigInt(TINYMALL_ORDER_UNCONFIRM);
    }

    public static Integer getOrderComment() {
        return getConfigInt(TINYMALL_ORDER_COMMENT);
    }

    public static String getMallName() {
        return getConfig(TINYMALL_MALL_NAME);
    }

    public static String getMallAddress() {
        return getConfig(TINYMALL_MALL_ADDRESS);
    }

    public static String getMallPhone() {
        return getConfig(TINYMALL_MALL_PHONE);
    }

    public static String getMallQQ() {
        return getConfig(TINYMALL_MALL_QQ);
    }

    public static String getMallLongitude() {
        return getConfig(TINYMALL_MALL_LONGITUDE);
    }

    public static String getMallLatitude() {
        return getConfig(TINYMALL_MALL_Latitude);
    }

    public static void setConfigs(Map<String, String> configs) {
        SYSTEM_CONFIGS = configs;
    }

    public static void updateConfigs(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            SYSTEM_CONFIGS.put(entry.getKey(), entry.getValue());
        }
    }
}

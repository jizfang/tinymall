package com.example.tinymall.core.config;

import com.example.tinymall.core.system.SystemConfig;
import com.example.tinymall.entity.TinymallSystem;
import com.example.tinymall.service.TinymallSystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SystemInistService
 * @Description
 * @Author jzf
 * @Date 2020-4-23 11:40
 */
@Component
public class SystemInistService {
    private SystemInistService systemInistService;

    @Autowired
    private Environment environment;

    @PostConstruct
    private void inist() {
        systemInistService = this;
        initConfigs();
    }


    private final static Map<String, String> DEFAULT_CONFIGS = new HashMap<>();

    static {
        // 小程序相关配置默认值
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_WX_INDEX_NEW, "6");
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_WX_INDEX_HOT, "6");
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_WX_INDEX_BRAND, "4");
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_WX_INDEX_TOPIC, "4");
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_WX_INDEX_CATLOG_LIST, "4");
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_WX_INDEX_CATLOG_GOODS, "4");
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_WX_SHARE, "true");
        // 运费相关配置默认值
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_EXPRESS_FREIGHT_VALUE, "8");
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_EXPRESS_FREIGHT_MIN, "88");
        // 订单相关配置默认值
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_ORDER_UNPAID, "30");
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_ORDER_UNCONFIRM, "7");
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_ORDER_COMMENT, "7");
        // 商城相关配置默认值
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_MALL_NAME, "tinymall");
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_MALL_ADDRESS, "广州");
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_MALL_Latitude, "23.13");
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_MALL_LONGITUDE, "113.27");
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_MALL_PHONE, "020-xxxx-xxxx");
        DEFAULT_CONFIGS.put(SystemConfig.TINYMALL_MALL_QQ, "");
    }

    @Autowired
    private TinymallSystemConfigService tinymallSystemConfigService;

    private void initConfigs() {
        // 1. 读取数据库全部配置信息
        Map<String, String> configs = tinymallSystemConfigService.queryAll();

        // 2. 分析DEFAULT_CONFIGS
        for (Map.Entry<String, String> entry : DEFAULT_CONFIGS.entrySet()) {
            if (configs.containsKey(entry.getKey())) {
                continue;
            }

            configs.put(entry.getKey(), entry.getValue());
            TinymallSystem tinymallSystem = new TinymallSystem();
            tinymallSystem.setKeyName(entry.getKey());
            tinymallSystem.setKeyValue(entry.getValue());
            tinymallSystemConfigService.insert(tinymallSystem);
        }

        SystemConfig.setConfigs(configs);
    }
}

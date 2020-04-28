package com.example.tinymall.core.wx;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName WxProperties
 * @Description
 * @Author jzf
 * @Date 2020-4-10 14:42
 */
@Configuration
@ConfigurationProperties(prefix = "tinymall.wx")
@Data
public class WxProperties {
    private String appId;

    private String appSecret;

    private String mchId;

    private String mchKey;

    private String notifyUrl;

    private String keyPath;
}

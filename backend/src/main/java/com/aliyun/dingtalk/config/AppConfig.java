package com.aliyun.dingtalk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "dingtalk")
public class AppConfig {

    private String corpId;

    private String appKey;

    private String appSecret;

    private String ownerKey;

    private String aesKey;

    private String token;

    private String processCode;

}

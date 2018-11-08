package com.lvqiang.springcloud.practice.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("prety")
@Component
@RefreshScope
public class PretyConfig {
    private String name;
    private Integer age;
}

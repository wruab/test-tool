package com.xiaoheibaby.test.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    /**
     * 可以从这里下载:
     * https://github.com/P3TERX/GeoLite.mmdb/releases
     */
    private String mmdbPath = "";
}

package com.xiaoheibaby.test.common.config;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

@Configuration
public class AppConfig {
    @Bean
    public DatabaseReader databaseReader() throws Exception {
        // 使用 ClassPathResource 加载文件
        ClassPathResource resource = new ClassPathResource("geolite/GeoLite2-City.mmdb");
        File databaseFile = resource.getFile();
        // 加载数据库到内存
        return new DatabaseReader.Builder(databaseFile).build();
    }
}

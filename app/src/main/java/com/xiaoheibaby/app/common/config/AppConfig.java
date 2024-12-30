package com.xiaoheibaby.app.common.config;

import com.maxmind.geoip2.DatabaseReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final AppProperties appProperties;

    @Bean
    public DatabaseReader databaseReader() {
        File databaseFile = new File(appProperties.getMmdbPath());
        try {
            return new DatabaseReader.Builder(databaseFile).build();
        } catch (Exception e) {
            log.error("error", e);
            return null;
        }
    }
}

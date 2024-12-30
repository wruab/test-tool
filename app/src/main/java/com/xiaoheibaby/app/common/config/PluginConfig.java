package com.xiaoheibaby.app.common.config;

import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class PluginConfig {
    private final AppProperties appProperties;

    public PluginConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public PluginManager pluginManager() {
        Path pluginsDirectory = Paths.get(appProperties.getPluginsPath());
        return new DefaultPluginManager(pluginsDirectory);
    }
}

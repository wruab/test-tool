package com.xiaoheibaby.test.common.config;

import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class PluginConfig {
    @Bean
    public PluginManager pluginManager() {
        Path pluginsDirectory = Paths.get("plugins");
        return new DefaultPluginManager(pluginsDirectory);
    }
}

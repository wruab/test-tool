package com.xiaoheibaby.app.service;

import com.xiaoheibaby.plugin.ToolPlugin;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.pf4j.PluginManager;
import org.pf4j.PluginState;
import org.pf4j.PluginWrapper;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PluginService {
    private final PluginManager pluginManager;

    public PluginService(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @PostConstruct
    public void pluginInit(){
        pluginManager.loadPlugins();
        pluginManager.startPlugins();
    }

    public void loadPlugin(Path pluginPath) {
        String pluginId = pluginManager.loadPlugin(pluginPath);
        pluginManager.startPlugin(pluginId);
    }

    public void unloadPlugin(String pluginId) {
        pluginManager.stopPlugin(pluginId);
        pluginManager.unloadPlugin(pluginId);
    }

    public void enablePlugin(String pluginId) {
        // 检查插件是否存在
        PluginWrapper pluginWrapper = pluginManager.getPlugin(pluginId);
        if (pluginWrapper == null) {
            throw new IllegalArgumentException("插件未找到，无法启用！");
        }

        // 获取插件状态
        PluginState state = pluginWrapper.getPluginState();
        switch (state) {
            case DISABLED:
                pluginManager.enablePlugin(pluginId);
                break;
            case STARTED:
                throw new IllegalStateException("插件已启用！");
            default:
                pluginManager.startPlugin(pluginId);
                break;
        }
    }

    public List<String> executeAllPlugins(String input) {
        // 获取所有实现 PluginFeature 接口的扩展
        List<ToolPlugin> features = pluginManager.getExtensions(ToolPlugin.class);

        // 执行每个扩展并收集结果
        return features.stream()
            .map(feature -> feature.testPrint(input))
            .collect(Collectors.toList());
    }
}

package com.xiaoheibaby.app.service;

import com.xiaoheibaby.app.common.copy.BeanCopier;
import com.xiaoheibaby.app.common.util.JsonUtil;
import com.xiaoheibaby.app.model.consts.ConfigDataConst;
import com.xiaoheibaby.app.model.dto.PluginDataDTO;
import com.xiaoheibaby.app.model.entity.PluginData;
import com.xiaoheibaby.plugin.extension.ToolPlugin;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PluginService {
    private final PluginManager pluginManager;
    private final PluginDBService pluginDBService;

    @PostConstruct
    private void pluginInit() {
        log.info("开始加载插件...");
        this.loadPlugin();
        log.info("加载插件完毕...");
    }

    public void loadPlugin() {
        pluginManager.loadPlugins();
        List<PluginWrapper> pluginWrapperList = pluginManager.getPlugins();
        for (PluginWrapper pluginItem : pluginWrapperList) {
            PluginData pluginDataItem = pluginDBService.queryByPluginId(pluginItem.getPluginId());
            if (pluginDataItem == null) {
                //数据库中不存在，则插入并且状态为未启用
                pluginDataItem = new PluginData();
                pluginDataItem.setId(pluginItem.getDescriptor().getPluginId());
                pluginDataItem.setName(pluginItem.getDescriptor().getPluginId());
                pluginDataItem.setDescription(pluginItem.getDescriptor().getPluginDescription());
                pluginDataItem.setVersion(pluginItem.getDescriptor().getVersion());
                pluginDataItem.setProvider(pluginItem.getDescriptor().getProvider());
                pluginDataItem.setDependencies(JsonUtil.toJson(pluginItem.getDescriptor().getDependencies()));
                pluginDataItem.setStatus(ConfigDataConst.STATUS_DISABLE);
                pluginDBService.insert(pluginDataItem);
            } else {
                if (pluginDataItem.getStatus() == ConfigDataConst.STATUS_ENABLE) {
                    pluginManager.startPlugin(pluginItem.getPluginId());
                }
            }
        }
    }

    /**
     * 加载插件
     */
    public void loadPlugin(Path pluginPath) {
        String pluginId = pluginManager.loadPlugin(pluginPath);
        pluginManager.startPlugin(pluginId);
    }

    /**
     * 卸载插件
     */
    public void unloadPlugin(String pluginId) {
        pluginManager.stopPlugin(pluginId);
        pluginManager.unloadPlugin(pluginId);
    }

    /**
     * 开启插件
     */
    public void enablePlugin(String pluginId) {
        this.changePluginStatus(ConfigDataConst.STATUS_ENABLE, pluginId);
    }

    /**
     * 关闭插件
     */
    public void disablePlugin(String pluginId) {
        this.changePluginStatus(ConfigDataConst.STATUS_DISABLE, pluginId);
    }

    /**
     * 禁用启用插件
     */
    public void changePluginStatus(int status, String pluginId) {
        // 检查插件是否存在
        PluginWrapper pluginWrapper = pluginManager.getPlugin(pluginId);
        if (pluginWrapper == null) {
            throw new RuntimeException("插件未找到，无法启用！");
        }
        if (status == ConfigDataConst.STATUS_DISABLE) {
            pluginManager.stopPlugin(pluginId);
            pluginManager.disablePlugin(pluginId);
        } else if (status == ConfigDataConst.STATUS_ENABLE) {
            pluginManager.enablePlugin(pluginId);
            pluginManager.startPlugin(pluginId);
        }

        // 更新数据库状态
        pluginDBService.updateStatus(pluginId, status);
    }

    public List<PluginDataDTO> pluginList() {
        List<PluginData> pluginDataList = pluginDBService.queryAllPlugin();
        return pluginDataList.stream().map(BeanCopier.INSTANCE::pluginDataToDTO)
            .collect(Collectors.toList());
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

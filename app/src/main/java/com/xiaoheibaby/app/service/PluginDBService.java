package com.xiaoheibaby.app.service;

import com.xiaoheibaby.app.mapper.PluginDataMapper;
import com.xiaoheibaby.app.model.entity.PluginData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 持久化插件信息
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PluginDBService {
    private final PluginDataMapper pluginDataMapper;

    public List<PluginData> queryAllPlugin() {
        return null;
    }
}

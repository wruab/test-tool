package com.xiaoheibaby.app.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoheibaby.app.mapper.PluginDataMapper;
import com.xiaoheibaby.app.model.consts.ConfigDataConst;
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
        return pluginDataMapper.selectList(new LambdaQueryWrapper<PluginData>()
            .ge(PluginData::getStatus, ConfigDataConst.STATUS_DELETE));
    }

    public PluginData queryByPluginId(String id) {
        return pluginDataMapper.selectOne(new LambdaQueryWrapper<PluginData>()
            .ge(PluginData::getStatus, ConfigDataConst.STATUS_DELETE)
            .eq(PluginData::getId, id)
            .last("limit 1")
        );
    }

    public int insert(PluginData pluginData) {
        return pluginDataMapper.insert(pluginData);
    }
}

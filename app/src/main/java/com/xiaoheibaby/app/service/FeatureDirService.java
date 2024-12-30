package com.xiaoheibaby.app.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoheibaby.app.mapper.FeatureDirMapper;
import com.xiaoheibaby.app.model.entity.FeatureDir;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模块，类似菜单展示
 */
@Service
@RequiredArgsConstructor
public class FeatureDirService {
    private final FeatureDirMapper featureDirMapper;
    private final PluginService pluginService;

    public List<FeatureDir> queryList() {
        List<FeatureDir> featureDirList = featureDirMapper.selectList(new LambdaQueryWrapper<FeatureDir>()
            .eq(FeatureDir::getStatus, 1));

        System.out.println(pluginService.executeAllPlugins("xiaohei"));

        return featureDirList;
    }


}

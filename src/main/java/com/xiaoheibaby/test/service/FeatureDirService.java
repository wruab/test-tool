package com.xiaoheibaby.test.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoheibaby.test.mapper.FeatureDirMapper;
import com.xiaoheibaby.test.model.entity.FeatureDir;
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

    public List<FeatureDir> queryList() {
        return featureDirMapper.selectList(new LambdaQueryWrapper<FeatureDir>()
            .eq(FeatureDir::getStatus, 1));
    }



}

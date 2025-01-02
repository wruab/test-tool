package com.xiaoheibaby.app.common.copy;

import com.xiaoheibaby.app.model.dto.PluginDataDTO;
import com.xiaoheibaby.app.model.entity.PluginData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeanCopier {
    BeanCopier INSTANCE = Mappers.getMapper(BeanCopier.class);

    PluginDataDTO pluginDataToDTO(PluginData source);
}

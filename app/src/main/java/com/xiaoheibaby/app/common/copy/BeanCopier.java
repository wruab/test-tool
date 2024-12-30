package com.xiaoheibaby.app.common.copy;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeanCopier {
    BeanCopier INSTANCE = Mappers.getMapper(BeanCopier.class);

}

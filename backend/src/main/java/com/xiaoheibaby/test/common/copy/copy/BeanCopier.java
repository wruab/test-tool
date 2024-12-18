package com.xiaoheibaby.test.common.copy.copy;

import com.xiaoheibaby.test.model.dto.UserDTO;
import com.xiaoheibaby.test.model.entity.User;
import com.xiaoheibaby.test.model.param.UserParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeanCopier {
    BeanCopier INSTANCE = Mappers.getMapper(BeanCopier.class);

    UserDTO userToDTO(User source);

    User userParamToEntity(UserParam source);

}

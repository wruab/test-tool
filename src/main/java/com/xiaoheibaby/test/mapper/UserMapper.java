package com.xiaoheibaby.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoheibaby.test.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

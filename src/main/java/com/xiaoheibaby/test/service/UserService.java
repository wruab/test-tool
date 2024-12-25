package com.xiaoheibaby.test.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xiaoheibaby.test.common.copy.BeanCopier;
import com.xiaoheibaby.test.mapper.UserMapper;
import com.xiaoheibaby.test.model.dto.UserDTO;
import com.xiaoheibaby.test.model.entity.User;
import com.xiaoheibaby.test.model.param.UserParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public List<UserDTO> selectAll() {
        return userMapper.selectList(new LambdaUpdateWrapper<>())
            .stream()
            .map(BeanCopier.INSTANCE::userToDTO)
            .collect(Collectors.toList());
    }

    public void addUser(UserParam userParam){
        User user = BeanCopier.INSTANCE.userParamToEntity(userParam);
        userMapper.insert(user);
    }
}

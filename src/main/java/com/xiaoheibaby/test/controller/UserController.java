package com.xiaoheibaby.test.controller;

import com.xiaoheibaby.test.model.dto.UserDTO;
import com.xiaoheibaby.test.model.param.UserParam;
import com.xiaoheibaby.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/list")
    public List<UserDTO> list() {
        return userService.selectAll();
    }

    @PostMapping("/add")
    public boolean list(@RequestBody UserParam userParam) {
        userService.addUser(userParam);
        return true;
    }
}

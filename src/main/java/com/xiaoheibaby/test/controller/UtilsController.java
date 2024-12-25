package com.xiaoheibaby.test.controller;

import cn.hutool.core.util.HashUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
public class UtilsController {

    @PostMapping("/hash")
    public void hash() {
//        HashUtil
    }
}

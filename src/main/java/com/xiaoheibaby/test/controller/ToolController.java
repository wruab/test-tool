package com.xiaoheibaby.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tool")
public class ToolController {

    @GetMapping("/json-format")
    public String jsonFormat() {
        return "tool/json-format";
    }
}

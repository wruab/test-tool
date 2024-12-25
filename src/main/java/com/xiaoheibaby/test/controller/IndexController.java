package com.xiaoheibaby.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String root(Model model) {
        return index(model);
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "index";
    }
}

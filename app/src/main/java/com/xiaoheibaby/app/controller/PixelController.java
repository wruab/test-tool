package com.xiaoheibaby.app.controller;

import com.xiaoheibaby.app.service.PixelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pixel")
public class PixelController {
    private final PixelService pixelService;


    @GetMapping({"", "/", "/index"})
    public String index(Model model) {
        pixelService.init();
        model.addAttribute("colors", pixelService.convertToColors());
        return "pixel/index";
    }
}

package com.xiaoheibaby.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequestMapping("/pixel")
public class PixelController {

    @GetMapping("")
    public String root(Model model) {
        return index(model);
    }

    @GetMapping("/index")
    public String index(Model model) {
        int rows = 400;
        int cols = 225;
        String[][] colors = new String[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                colors[i][j] = String.format("#%02X%02X%02X", random.nextInt(256), random.nextInt(256), random.nextInt(256));
            }
        }
        model.addAttribute("rows", rows);
        model.addAttribute("cols", cols);
        model.addAttribute("colors", colors);
        return "pixel/index";
    }
}

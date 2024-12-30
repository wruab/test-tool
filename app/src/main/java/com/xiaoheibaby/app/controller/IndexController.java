package com.xiaoheibaby.app.controller;

import com.xiaoheibaby.app.model.entity.FeatureDir;
import com.xiaoheibaby.app.service.FeatureDirService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final FeatureDirService featureDirService;

    @GetMapping({"/index", "/", ""})
    public String index(Model model) {
        List<FeatureDir> featureDirList = featureDirService.queryList();
        model.addAttribute("featureDirList", featureDirList);
        return "index";
    }
}

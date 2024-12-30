package com.xiaoheibaby.test.controller;

import com.xiaoheibaby.test.model.entity.FeatureDir;
import com.xiaoheibaby.test.service.FeatureDirService;
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

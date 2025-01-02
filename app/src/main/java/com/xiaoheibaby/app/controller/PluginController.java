package com.xiaoheibaby.app.controller;

import com.xiaoheibaby.app.model.dto.PluginDataDTO;
import com.xiaoheibaby.app.service.PluginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/plugin")
public class PluginController {
    private final PluginService pluginService;

    @RequestMapping({"/index", "/", ""})
    public String index(Model model) {
        List<PluginDataDTO> pluginDataDTOList = pluginService.pluginList();
        model.addAttribute("pluginDataDTOList", pluginDataDTOList);
        return "plugin/index";
    }

    @GetMapping("/enable/{pluginId}")
    public String enablePlugin(@PathVariable String pluginId, Model model) {
        pluginService.enablePlugin(pluginId);
        return this.index(model);
    }

    @GetMapping("/disable/{pluginId}")
    public String disablePlugin(@PathVariable String pluginId, Model model) {
        pluginService.disablePlugin(pluginId);
        return this.index(model);
    }

    // 保存上传的插件文件
    private Path savePluginFile(MultipartFile file) {
        // 实现文件保存逻辑
        return null;
    }

    @ResponseBody
    @PostMapping("/upload")
    public String uploadPlugin(@RequestParam("file") MultipartFile file) {
        Path pluginPath = savePluginFile(file);
        pluginService.loadPlugin(pluginPath);
        return "插件上传并加载成功！";
    }
}

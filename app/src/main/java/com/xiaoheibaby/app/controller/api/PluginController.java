package com.xiaoheibaby.app.controller.api;

import com.xiaoheibaby.app.service.PluginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plugins")
public class PluginController {
    private final PluginService pluginService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPlugin(@RequestParam("file") MultipartFile file) {
        Path pluginPath = savePluginFile(file);
        pluginService.loadPlugin(pluginPath);
        return ResponseEntity.ok("插件上传并加载成功！");
    }

    @PostMapping("/{pluginId}/enable")
    public ResponseEntity<String> enablePlugin(@PathVariable String pluginId) {
        pluginService.enablePlugin(pluginId);
        return ResponseEntity.ok("插件已启用！");
    }

    @DeleteMapping("/{pluginId}")
    public ResponseEntity<String> deletePlugin(@PathVariable String pluginId) {
        pluginService.unloadPlugin(pluginId);
        return ResponseEntity.ok("插件已卸载！");
    }

    // 保存上传的插件文件
    private Path savePluginFile(MultipartFile file) {
        // 实现文件保存逻辑
        return null;
    }
}

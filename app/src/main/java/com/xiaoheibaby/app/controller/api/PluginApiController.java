package com.xiaoheibaby.app.controller.api;

import com.xiaoheibaby.app.service.PluginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plugin-api/")
public class PluginApiController {
    private final PluginService pluginService;


}

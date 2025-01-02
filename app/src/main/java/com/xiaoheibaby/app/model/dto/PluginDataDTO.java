package com.xiaoheibaby.app.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PluginDataDTO {
    private String id;

    private String name;

    private String description;

    private String version;

    private String provider;

    private String dependencies;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}

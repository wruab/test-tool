package com.xiaoheibaby.app.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("plugin_data")
public class PluginData {
    @TableId("id")
    private String id;

    @TableField("name")
    private String name;

    @TableField("version")
    private String version;

    @TableField("provider")
    private String provider;

    @TableField("dependencies")
    private String dependencies;

    @TableField("status")
    private Integer status;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}

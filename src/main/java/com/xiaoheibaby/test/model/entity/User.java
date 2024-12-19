package com.xiaoheibaby.test.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    @TableField("username")
    private String username;

    @TableField("password")
    private String password;
}

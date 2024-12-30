package com.xiaoheibaby.app.common.config;

import com.baomidou.mybatisplus.extension.ddl.SimpleDdl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataBaseDdl extends SimpleDdl {

    /**
     * 获取要执行的SQL脚本文件列表
     */
    @Override
    public List<String> getSqlFiles() {
        return List.of("db/schema.sql");
    }
}

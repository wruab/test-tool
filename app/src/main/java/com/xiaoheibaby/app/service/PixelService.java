package com.xiaoheibaby.app.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaoheibaby.app.common.util.JsonUtil;
import com.xiaoheibaby.app.mapper.ConfigDataMapper;
import com.xiaoheibaby.app.model.consts.ConfigDataConst;
import com.xiaoheibaby.app.model.dto.PixelData;
import com.xiaoheibaby.app.model.entity.ConfigData;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class PixelService {
    public static final int rows = 400;
    public static final int cols = 225;

    private final ConfigDataMapper configDataMapper;

    @PostConstruct
    public void init() {
        ConfigData dbPixelData = configDataMapper.selectOne(new LambdaUpdateWrapper<ConfigData>()
            .eq(ConfigData::getName, ConfigDataConst.PIXEL_DATA_NAME));
        if (dbPixelData != null) {
            return;
        }
        List<PixelData> pixelDataList = new ArrayList<>();
        Random random = new Random();
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                PixelData item = new PixelData();
                item.setX(x);
                item.setY(y);
                item.setColor(String.format("#%02X%02X%02X", random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                pixelDataList.add(item);
            }
        }

        String json = JsonUtil.toJson(pixelDataList);

        ConfigData initPixelData = new ConfigData();
        initPixelData.setName(ConfigDataConst.PIXEL_DATA_NAME);
        initPixelData.setData(json);
        configDataMapper.insert(initPixelData);
    }

    public List<PixelData> queryAllPixel() {
        ConfigData dbPixelData = configDataMapper.selectOne(new LambdaUpdateWrapper<ConfigData>()
            .eq(ConfigData::getName, ConfigDataConst.PIXEL_DATA_NAME));
        if (dbPixelData == null) {
            return new ArrayList<>();
        }
        return JsonUtil.fromJson(dbPixelData.getData(), new TypeReference<>() {
        });
    }

    public String[][] convertToColors() {
        String[][] colors = new String[rows][cols];
        for (PixelData pixelData : queryAllPixel()) {
            int x = pixelData.getX(); // 列
            int y = pixelData.getY(); // 行
            String color = pixelData.getColor(); // 颜色
            colors[x][y] = color;
        }
        return colors;
    }

    public void updateColor(int x, int y, String color) {
        List<PixelData> pixelDataList = queryAllPixel();
        pixelDataList.stream()
            .filter(item -> item.getX() == x && item.getY() == y)
            .forEach(item -> item.setColor(color));
        configDataMapper.update(new LambdaUpdateWrapper<ConfigData>()
            .set(ConfigData::getData, JsonUtil.toJson(pixelDataList))
            .eq(ConfigData::getName, ConfigDataConst.PIXEL_DATA_NAME));
    }
}

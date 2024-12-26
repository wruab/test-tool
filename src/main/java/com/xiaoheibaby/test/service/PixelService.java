package com.xiaoheibaby.test.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaoheibaby.test.common.util.JsonUtil;
import com.xiaoheibaby.test.mapper.ConfigDataMapper;
import com.xiaoheibaby.test.model.consts.ConfigDataConst;
import com.xiaoheibaby.test.model.dto.PixelData;
import com.xiaoheibaby.test.model.entity.ConfigData;
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

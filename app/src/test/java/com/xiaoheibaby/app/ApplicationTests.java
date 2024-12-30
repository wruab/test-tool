package com.xiaoheibaby.app;

import com.xiaoheibaby.app.model.dto.PixelData;
import com.xiaoheibaby.app.service.PixelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private PixelService pixelService;

    @Test
    void contextLoads() {
        List<PixelData> duckPixels = new ArrayList<>();

        // 鸭子的头部（黄色）
        duckPixels.add(new PixelData(10, 10, "yellow"));
        duckPixels.add(new PixelData(11, 10, "yellow"));
        duckPixels.add(new PixelData(10, 11, "yellow"));
        duckPixels.add(new PixelData(11, 11, "yellow"));
        duckPixels.add(new PixelData(12, 10, "yellow"));

        // 鸭子的身体（黄色）
        for (int x = 8; x < 14; x++) { // 水平坐标 x
            for (int y = 12; y < 16; y++) { // 垂直坐标 y
                duckPixels.add(new PixelData(x, y, "yellow"));
            }
        }

        // 鸭子的喙（橙色）
        duckPixels.add(new PixelData(12, 12, "orange"));
        duckPixels.add(new PixelData(13, 12, "orange"));
        duckPixels.add(new PixelData(12, 13, "orange"));

        // 鸭子的眼睛（黑色）
        duckPixels.add(new PixelData(9, 9, "black"));

        // 鸭子的翅膀（黄色）
        duckPixels.add(new PixelData(7, 13, "yellow"));
        duckPixels.add(new PixelData(14, 13, "yellow"));

        // 批量更新鸭子像素
        for (PixelData pixel : duckPixels) {
            pixelService.updateColor(pixel.getX(), pixel.getY(), pixel.getColor());
        }
    }

}

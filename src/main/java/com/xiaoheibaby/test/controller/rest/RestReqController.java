package com.xiaoheibaby.test.controller.rest;

import com.xiaoheibaby.test.model.dto.IpInfoDTO;
import com.xiaoheibaby.test.model.dto.PixelData;
import com.xiaoheibaby.test.service.GeoLiteService;
import com.xiaoheibaby.test.service.PixelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Slf4j
@RequestMapping("/b")
@RestController
@RequiredArgsConstructor
public class RestReqController {
    private final GeoLiteService geoLiteService;
    private final PixelService pixelService;

    /**
     * ip查询
     */
    @RequestMapping("/ip-query")
    public IpInfoDTO ipQuery(String host) {
        return geoLiteService.ipQuery(host);
    }

    /**
     * 返回随机状态码
     */
    @GetMapping("/random-status")
    public ResponseEntity<String> randomCode() {
        HttpStatus[] httpStatuses = HttpStatus.values();
        Random random = new Random();
        HttpStatus randomStatus = httpStatuses[random.nextInt(httpStatuses.length)];
        return new ResponseEntity<>("Random Status: " + randomStatus.value() + " " + randomStatus.getReasonPhrase(), randomStatus);
    }

    /**
     * 返回随机的延迟时间（10秒内）
     */
    @GetMapping("/random-delay")
    public String getRandomDelay() throws InterruptedException {
        Random random = new Random();
        int delaySeconds = random.nextInt(10) + 1;
        log.info("Delaying for {} seconds...", delaySeconds);
        // 模拟延迟
        Thread.sleep(delaySeconds * 1000L);
        // 返回延迟的时间
        return "Delayed for " + delaySeconds + " seconds.";
    }

    @GetMapping("/pixel-init")
    public void pixelInit() {
        pixelService.init();
    }

    @GetMapping("/pixel-export")
    public List<PixelData> pixelExport() {
        return pixelService.queryAllPixel();
    }
}

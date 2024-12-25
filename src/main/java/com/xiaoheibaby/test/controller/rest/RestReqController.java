package com.xiaoheibaby.test.controller.rest;

import com.xiaoheibaby.test.model.dto.IpInfoDTO;
import com.xiaoheibaby.test.service.GeoLiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/b")
@RestController
@RequiredArgsConstructor
public class RestReqController {

    private final GeoLiteService geoLiteService;

    @GetMapping("/ip-query")
    public IpInfoDTO ipQuery(String host) {
        return geoLiteService.ipQuery(host);
    }
}

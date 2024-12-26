package com.xiaoheibaby.test.controller;

import com.xiaoheibaby.test.common.util.ServletUtils;
import com.xiaoheibaby.test.model.dto.IpInfoDTO;
import com.xiaoheibaby.test.service.GeoLiteService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tool")
public class ToolController {

    private final GeoLiteService geoLiteService;

    @GetMapping("/json-format")
    public String jsonFormat() {
        return "tool/json-format";
    }

    @GetMapping("/ip-query")
    public String ipQuery(String ip, Model model) {
        if (StringUtils.isEmpty(ip)) {
            ip = ServletUtils.getRequestIp();
        } else {
            ip = ip.trim();
        }
        IpInfoDTO ipInfoDTO = geoLiteService.ipQuery(ip);
        model.addAttribute("ip", ip);
        model.addAttribute("country", ipInfoDTO.getCountryName());
        model.addAttribute("city", ipInfoDTO.getCityName());
        model.addAttribute("latitude", ipInfoDTO.getLatitude());
        model.addAttribute("longitude", ipInfoDTO.getLongitude());
        return "tool/ip-query";
    }
}

package com.xiaoheibaby.test.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.xiaoheibaby.test.model.dto.IpInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Service
public class GeoLiteService {

    @Autowired(required = false)
    private DatabaseReader databaseReader;

    public IpInfoDTO ipQuery(String host) {
        IpInfoDTO ipInfoDTO = new IpInfoDTO();
        try {
            InetAddress ipAddress = InetAddress.getByName(host);
            CityResponse cityResponse = databaseReader.city(ipAddress);
            ipInfoDTO.setCityName(cityResponse.getCity().getName());
            ipInfoDTO.setCountryName(cityResponse.getCountry().getName());
            ipInfoDTO.setLatitude(cityResponse.getLocation().getLatitude());
            ipInfoDTO.setLongitude(cityResponse.getLocation().getLongitude());
        } catch (Exception ignore) {
        }
        return ipInfoDTO;
    }
}

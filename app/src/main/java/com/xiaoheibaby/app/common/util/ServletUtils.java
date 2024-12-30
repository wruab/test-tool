package com.xiaoheibaby.app.common.util;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ArrayUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;


public class ServletUtils {

    private ServletUtils() {
    }

    /**
     * 获取当前请求
     *
     * @return an optional http servlet request
     */
    @NonNull
    public static Optional<HttpServletRequest> getCurrentRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
            .filter(requestAttributes -> requestAttributes instanceof ServletRequestAttributes)
            .map(requestAttributes -> (ServletRequestAttributes) requestAttributes)
            .map(ServletRequestAttributes::getRequest);
    }

    /**
     * 获取当前请求ip
     *
     * @return ip address or null
     */
    @Nullable
    public static String getRequestIp() {
        return getCurrentRequest().map(ServletUtils::getClientIP).orElse(null);
    }

    @Nullable
    public static String getRequestUrl() {
        return getCurrentRequest().map(HttpServletRequest::getRequestURL).map(String::valueOf).orElse(null);
    }

    public static String getClientIP(HttpServletRequest request, String... otherHeaderNames) {
        String[] headers = {"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        if (ArrayUtil.isNotEmpty(otherHeaderNames)) {
            headers = ArrayUtil.addAll(headers, otherHeaderNames);
        }

        return getClientIPByHeader(request, headers);
    }

    public static String getClientIPByHeader(HttpServletRequest request, String... headerNames) {
        String ip;
        for (String header : headerNames) {
            ip = request.getHeader(header);
            if (!NetUtil.isUnknown(ip)) {
                return NetUtil.getMultistageReverseProxyIp(ip);
            }
        }

        ip = request.getRemoteAddr();
        return NetUtil.getMultistageReverseProxyIp(ip);
    }

}

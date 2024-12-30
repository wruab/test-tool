package com.xiaoheibaby.app.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class JsonUtil {
    private static final ObjectMapper INSTANCE = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return INSTANCE;
    }

    public static final ObjectMapper defaultJsonMapper = createDefaultJsonMapper();

    public static ObjectMapper createDefaultJsonMapper() {
        return createDefaultJsonMapper(null);
    }

    @NonNull
    public static ObjectMapper createDefaultJsonMapper(@Nullable PropertyNamingStrategy strategy) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if (strategy != null) {
            mapper.setPropertyNamingStrategy(strategy);
        }
        return mapper;
    }

    public static String toJson(Object obj) {
        try {
            return defaultJsonMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("json序列化[{}]异常", obj, e);
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> clasz) {
        try {
            return defaultJsonMapper.readValue(json, clasz);
        } catch (IOException e) {
            log.error("json反序列化[{}]异常", json, e);
            return null;
        }
    }

    public static <T> T fromJson(Object obj, Class<T> clasz) {
        return defaultJsonMapper.convertValue(obj, clasz);
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return defaultJsonMapper.readValue(json, typeReference);
        } catch (IOException e) {
            log.error("json反序列化[{}]异常", json, e);
            return null;
        }
    }

    public static <T> T jsonToObject(String json, Class<T> type) {
        try {
            return defaultJsonMapper.readValue(json, type);
        } catch (IOException e) {
            log.error("json反序列化[{}]异常", json, e);
            return null;
        }
    }

    public static <T> T jsonToObject(String json, TypeReference<T> typeReference) {
        try {
            return defaultJsonMapper.readValue(json, typeReference);
        } catch (IOException e) {
            log.error("json反序列化[{}]异常", json, e);
            return null;
        }
    }

    public static <T> T mapToObject(@NonNull Map<String, ?> sourceMap, @NonNull Class<T> type) {
        Assert.notEmpty(sourceMap, "Source map must not be empty");
        String json = toJson(sourceMap);
        return jsonToObject(json, type);
    }

    public static Map<String, Object> toMap(Object obj) {
        return defaultJsonMapper.convertValue(obj, new TypeReference<>() {
        });
    }
}

package org.grant.zm.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * ZoomGrant 2020/2/28
 */
@Slf4j
public class GJsonUtils {
    private static ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
        //格式美化
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //反序列化忽略未知字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //在序列化时忽略值为 null 的属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //解决转map数值科学计数法问题
        objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

    }

    public static String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T toBean(String json, Class<T> cl){
        try {
            return objectMapper.readValue(json, cl);
        } catch (IOException e) {
            log.error("json to bean error", e);
            return null;
        }
    }
}

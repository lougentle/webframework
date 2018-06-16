package com.sarakeal.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将POJO转换为Json
      * @param model
     * @param <T>
     * @return
     */
    public static <T> String toJson(T model) {
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(model);
        } catch (IOException e) {
            LOGGER.error("Failed to convert POJO to JSON", e);
            throw new RuntimeException(e);
        }
        return json;
    }
}

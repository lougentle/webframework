package com.sarakeal.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CodeUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeUtil.class);

    /**
     * 解码
     * @param source
     * @return
     */
    public static String decodeURL(String source) {
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Failed to decode URL", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 编码
     * @param source
     * @return
     */
    public static String encodeURL(String source) {
        String target;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Failed to encode URL", e);
            throw new RuntimeException(e);
        }
        return target;
    }

}

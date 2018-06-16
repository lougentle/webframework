package com.sarakeal.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StreamUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

    public static String getString(ServletInputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bf.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            LOGGER.error("failed to get string", e);
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}

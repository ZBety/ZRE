package org.sdk.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;

public class ObjectUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TypeReference<HashMap<String, Object>> MAP_TYPE =
            new TypeReference<HashMap<String, Object>>() {};

    static {
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.enable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
        objectMapper.enable(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
    }

    public static <T> T convert(Object data, TypeReference<T> type) {
        return objectMapper.convertValue(data, type);
    }

    public static <T> T convert(Object data, Class<T> clazz) {
        return objectMapper.convertValue(data, objectMapper.constructType(clazz));
    }

    public static Map<String, Object> convert(Object data) {
        return objectMapper.convertValue(data, MAP_TYPE);
    }

    public static Map<String, Object> fromJsonStringToMap(String jsonString) throws Exception {
        return JsonUtils.fromJsonStringToMap(jsonString);
    }

    public static String unzip(String payloadString) throws IOException {
        String content = null;
        try {
            if (payloadString == null || payloadString.trim().isEmpty()) {
                return null;
            } else {
                String decompressed = new String(
                        new GZIPInputStream(
                                new ByteArrayInputStream(
                                        Base64.getDecoder().decode(payloadString)
                                )
                        ).readAllBytes()
                );
                log.trace("accept compressed message '{}'", decompressed);
                content = decompressed;
            }
        } catch (ZipException e) {
            log.debug("Failed to decompress message content '{}'.", payloadString, e);
        } catch (Exception e) {
            log.trace("Failed to decode base64-message content '{}'.", payloadString, e);
            content = payloadString;
        }
        return content;
    }

    private static final Logger log = LoggerFactory.getLogger(ObjectUtil.class);
}


package com.example.ruleEngine.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_ABSENT)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final ObjectMapper deserializationObjectMapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)
            .disable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES)
            .disable(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES)
            .disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)
            .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
            .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);

    public static <T> String toJson(T obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> byte[] toJsonByteArray(T payload) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(payload);
    }

    public static JsonNode parseObject(String data) throws JsonProcessingException {
        return deserializationObjectMapper.readTree(data);
    }

    public static <T> T fromJsonString(String jsonString, TypeReference<T> type) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, type);
    }

    public static JsonNode fromJsonStringToJsonNode(String jsonString) throws JsonProcessingException {
        return objectMapper.readTree(jsonString);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> fromJsonStringToMap(String json) throws JsonProcessingException {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            log.error("fromJsonStringToMap error: {}, data:{}", e.getMessage(), json);
            throw e;
        }
    }
}

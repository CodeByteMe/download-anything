package top.cyblogs.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * JSON工具类
 *
 * @author CY
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {
    }

    public static JsonNode toJsonNode(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            return new ObjectNode(null);
        }
    }

    /**
     * JSON转换对象
     *
     * @param json  JSON String
     * @param clazz Class
     * @param <T>   Class Type
     * @return 规定类型对象
     * @author CY 已测试
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("JSON反序列化失败");
        }
    }

    /**
     * 对象到JSON
     *
     * @param object 对象
     * @return JSON String
     * @author CY 已测试
     */
    public static String toString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("JSON序列化失败");
        }
    }

    /**
     * 对象到JSON忽略null
     *
     * @param object 对象
     * @return JSON String
     * @author CY 已测试
     */
    public static String toStringIgnoreNull(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("JSON序列化失败");
        }
    }
}

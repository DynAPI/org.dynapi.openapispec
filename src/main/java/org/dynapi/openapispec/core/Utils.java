package org.dynapi.openapispec.core;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility methods that are used throughout the project
 */
public class Utils {
    private final static Map<Integer, String> code2text = new HashMap<>();

    static {
        code2text.put(1, "Informational");
        code2text.put(2, "Successful Operation");
        code2text.put(3, "Redirect");
        code2text.put(4, "Bad Request");
        code2text.put(5, "Server Error");
    }

    /**
     * @param status http status-code
     * @return http response class
     */
    public static String statusCodeToText(int status) {
        return code2text.getOrDefault(status / 100, "Unknown Operation");
    }

    /**
     * returns null or openapi-spec
     * @param specAble object to get the OpenApi-Spec from
     * @return {@code OpenApiSpecAble.getOpenApiSpec() result} or null
     */
    public static<V extends OpenApiSpecAble> JSONObject getOpenApiSpec(V specAble) {
        return specAble == null ? null : specAble.getOpenApiSpec();
    }

    /**
     * maps {@code OpenApiSpecAble} values of the array
     * @param array array
     * @return list without {@code OpenApiSpecAble}
     */
    public static<V extends OpenApiSpecAble> List<JSONObject> mapOpenApiSpecAble(V[] array) {
        return mapOpenApiSpecAble(List.of(array));
    }

    /**
     * maps {@code OpenApiSpecAble} values of the list
     * @param list list
     * @return list without {@code OpenApiSpecAble}
     */
    public static<V extends OpenApiSpecAble> List<JSONObject> mapOpenApiSpecAble(List<V> list) {
        if (list == null) return null;
        return list.stream().map(Utils::getOpenApiSpec).toList();
    }

    /**
     * maps the values of a map to the {@code OpenApiSpecAble.getOpenApiSpec()} result
     * @param map map to transform
     * @return map without {@code OpenApiSpecAble}
     */
    public static<V extends OpenApiSpecAble> Map<String, JSONObject> mapOpenApiSpecAble(Map<String, V> map) {
        if (map == null) return null;
        return map.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> getOpenApiSpec(e.getValue())));
    }
}

package org.dynapi.openapispec.core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility methods that are used throughout the project
 */
public class Utils {
    /**
     * returns null or openapi-spec
     * @param specAble object to get the OpenApi-Spec from
     * @return {@link OpenApiSpecAble#getOpenApiSpec() result} or null
     */
    public static <V extends OpenApiSpecAble> JSONObject getOpenApiSpec(V specAble) {
        return specAble == null ? null : specAble.getOpenApiSpec();
    }

    /**
     * maps {@link OpenApiSpecAble} values of the list
     * @param list list of the elements to transform
     * @return list without {@link OpenApiSpecAble}
     */
    public static <V extends OpenApiSpecAble> List<JSONObject> mapOpenApiSpecAble(List<V> list) {
        if (list == null || list.isEmpty()) return null;
        return list.stream().map(Utils::getOpenApiSpec).toList();
    }

    /**
     * like {@link Utils#mapOpenApiSpecAble(Map)} but doesn't return an empty {@link JSONArray} if null
     * @param list list of the elements to transform
     * @return list without {@link OpenApiSpecAble}
     */
    public static <V extends OpenApiSpecAble> JSONArray mapOpenApiSpecAble2Json(List<V> list) {
        List<JSONObject> result = mapOpenApiSpecAble(list);
        return result == null ? null : new JSONArray(result);
    }

    /**
     * maps the values of a map to the {@link OpenApiSpecAble#getOpenApiSpec()} result
     * @param map map to transform
     * @return map without {@link OpenApiSpecAble}
     */
    public static <V extends OpenApiSpecAble> Map<String, JSONObject> mapOpenApiSpecAble(Map<String, V> map) {
        if (map == null || map.isEmpty()) return null;
        return map.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> getOpenApiSpec(e.getValue())));
    }

    /**
     * like {@link Utils#mapOpenApiSpecAble(Map)} but doesn't return an empty {@link JSONObject} if null
     * @param map map to transform
     * @return map without {@link OpenApiSpecAble}
     */
    public static <V extends OpenApiSpecAble> JSONObject mapOpenApiSpecAble2Json(Map<String, V> map) {
        Map<String, JSONObject> result = mapOpenApiSpecAble(map);
        return result == null ? null : new JSONObject(result);
    }

//    public static JSONObject mapCallbacks(Map<String, Map<String, PathItem>> callbacks) {
//        if (callbacks == null || callbacks.isEmpty()) return null;
//        Map<String, Map<String, JSONObject>> result = callbacks.entrySet()
//                .stream()
//                .map(entry -> (Map.Entry<String, JSONObject>) new AbstractMap.SimpleEntry<>(entry.getKey(), Utils.mapOpenApiSpecAble2Json(entry.getValue())))
//                .filter(entry -> entry.getValue() != null)
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        return result.isEmpty() ? null : new JSONObject(result);
//    }
}

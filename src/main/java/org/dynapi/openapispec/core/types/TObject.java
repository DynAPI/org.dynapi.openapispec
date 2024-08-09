package org.dynapi.openapispec.core.types;

import lombok.NonNull;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ToString(callSuper = true)
public class TObject extends Schema<TObject> {
    private final Map<String, Schema<?>> properties = new HashMap<>();

    public TObject() {
        super();
    }

    /**
     * @param properties marks properties that are required
     */
    public TObject required(@NonNull String... properties) {
        ((JSONArray) options.getOrDefault("required", new JSONArray())).putAll(properties);
        return this;
    }

    /**
     * @param property property-name
     * @param value value schema
     */
    public TObject addProperty(@NonNull String property, @NonNull Schema<?> value) {
        properties.put(property, value);
        return this;
    }

    /**
     * marks that this object accepts properties not specified via {@code addProperty}
     */
    public TObject allowAdditionalProperties() {
        return allowAdditionalProperties(true);
    }
    /**
     * marks that this object accepts properties not specified via {@code addProperty}
     */
    public TObject allowAdditionalProperties(boolean allowAdditionalProperties) {
        options.put("additionalProperties", allowAdditionalProperties);
        return this;
    }

    /**
     * marks that this object accepts properties not specified via {@code addProperty} with a specific schema
     */
    public TObject additionalPropertiesOfSchema(@NonNull Schema<?> schema) {
        options.put("additionalProperties", schema);
        return this;
    }

    public TObject minProperties(int minProperties) {
        options.put("minProperties", minProperties);
        return this;
    }

    public TObject maxProperties(int maxProperties) {
        options.put("maxProperties", maxProperties);
        return this;
    }

    @Override
    protected JSONObject finalized() {
        if (properties.isEmpty())
            return new JSONObject()
                    .put("type", "object");

        Map<String, JSONObject> finalizedProperties = properties.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().finalized()));

        return new JSONObject()
                .put("type", "object")
                .put("properties", finalizedProperties);
    }
}

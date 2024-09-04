package org.dynapi.openapispec.core.schema;

import lombok.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TObject extends Schema<TObject, JSONObject> {
    private final Map<String, Schema<?, ?>> properties = new HashMap<>();

    /**
     * @param properties marks properties that are required
     */
    public TObject required(@NonNull String... properties) {
        if (!options.containsKey("required")) options.put("required", new JSONArray());
        ((JSONArray) options.get("required")).putAll(properties);
        return this;
    }

    /**
     * @param property property-name
     * @param value value schema
     */
    public TObject addProperty(@NonNull String property, @NonNull Schema<?, ?> value) {
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
    public TObject additionalPropertiesOfSchema(@NonNull Schema<?, ?> schema) {
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
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getOpenApiSpec()));

        return new JSONObject()
                .put("type", "object")
                .put("properties", finalizedProperties);
    }
}

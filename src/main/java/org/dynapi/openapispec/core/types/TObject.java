package org.dynapi.openapispec.core.types;

import lombok.NonNull;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString(callSuper = true)
public class TObject extends Schema<TObject> {
    private final Map<String, Schema<?>> properties = new HashMap<>();

    public TObject() {
        super();
    }

    public TObject required(@NonNull String... properties) {
        options.put("required", List.of(properties));
        return this;
    }

    public TObject addProperty(@NonNull String property, @NonNull Schema<?> value) {
        properties.put(property, value);
        return this;
    }

    public TObject allowAdditionalProperties() {
        return allowAdditionalProperties(true);
    }
    public TObject allowAdditionalProperties(boolean allowAdditionalProperties) {
        options.put("additionalProperties", allowAdditionalProperties);
        return this;
    }

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

        Map<String, JSONObject> finalizedProperties = new HashMap<>();
        for (Map.Entry<String, Schema<?>> entry : properties.entrySet()) {
            finalizedProperties.put(entry.getKey(), entry.getValue().finalized());
        }

        return new JSONObject()
                .put("type", "object")
                .put("properties", finalizedProperties);
    }
}

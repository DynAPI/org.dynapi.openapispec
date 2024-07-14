package org.dynapi.openapispec.core.types;

import lombok.ToString;
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

    public TObject required(String... properties) {
        options.put("required", List.of(properties));
        return this;
    }

    public TObject addProperty(String property, Schema<?> value) {
        properties.put(property, value);
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

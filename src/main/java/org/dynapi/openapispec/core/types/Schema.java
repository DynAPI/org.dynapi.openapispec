package org.dynapi.openapispec.core.types;

import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
@SuppressWarnings("unchecked")
public abstract class Schema<T extends Schema<T>> implements OpenApiSpecAble {
    protected final Map<String, Object> options;

    public Schema() {
        this.options = new HashMap<>();
    }

    public T description(String description) {
        options.put("description", description);
        return (T) this;
    }

    public T defaultValue(Object value) {
        options.put("default", value);
        return (T) this;
    }

    public T nullable() { return nullable(true); };
    public T nullable(boolean isNullable) {
        options.put("nullable", isNullable);
        return (T) this;
    }

    public T example(String... example) {
        options.put("example", example);
        return (T) this;
    }

    public JSONObject getOpenApiSpec() {
        Map<String, Object> combined = new HashMap<>();
        combined.putAll(options);
        combined.putAll(finalized().toMap());
        return new JSONObject(combined);
    }

    protected abstract JSONObject finalized();
}

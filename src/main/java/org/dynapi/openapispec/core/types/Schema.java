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

    protected T getThis() { return (T) this; }

    public T description(String description) {
        options.put("description", description);
        return getThis();
    }

    public T defaultValue(Object value) {
        options.put("default", value);
        return getThis();
    }

    public T nullable() { return nullable(true); };
    public T nullable(boolean isNullable) {
        options.put("nullable", isNullable);
        return getThis();
    }

    public T example(String... example) {
        options.put("example", example);
        return getThis();
    }

    public JSONObject getOpenApiSpec() {
        Map<String, Object> combined = new HashMap<>();
        combined.putAll(options);
        combined.putAll(finalized().toMap());
        return new JSONObject(combined);
    }

    protected abstract JSONObject finalized();
}

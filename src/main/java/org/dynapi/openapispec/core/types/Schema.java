package org.dynapi.openapispec.core.types;

import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@ToString
@SuppressWarnings("unchecked")
public abstract class Schema<T extends Schema<T>> implements OpenApiSpecAble {
    protected final Map<String, Object> options;

    public Schema() {
        this.options = new HashMap<>();
    }

    protected T getThis() { return (T) this; }

    /**
     * @param description schema description
     */
    public T description(@NonNull String description) {
        options.put("description", description);
        return getThis();
    }

    /**
     * @param value default-value if absent
     */
    public T defaultValue(Object value) {
        options.put("default", value);
        return getThis();
    }

    /**
     * marks that this field can be {@code null}
     */
    public T nullable() { return nullable(true); };
    /**
     * marks that this field can be {@code null}
     */
    public T nullable(boolean isNullable) {
        options.put("nullable", isNullable);
        return getThis();
    }

    /**
     * adds on or more examples to this schema
     * @param examples one or more
     */
    public T example(@NonNull Object... examples) {
        if (examples.length == 1) {
            options.put("example", examples[0]);
        } else {
            options.put("examples", new JSONArray(examples));
        }
        return getThis();
    }

    /**
     * specify the only allowed options
     * @param options options that are allowed
     */
    public T options(@NonNull Object... options) {
        this.options.put("enum", new JSONArray(options));
        return getThis();
    }

    /**
     * marks this property as only used in GET (not POST/PUT/PATCH)<br>
     * note: only for direct properties of {@code TObject}
     */
    public T readOnly() {
        options.remove("writeOnly");
        this.options.put("readOnly", true);
        return getThis();
    }

    /**
     * marks this property as only used in POST/PUT/PATCH (not GET)<br>
     * note: only for direct properties of {@code TObject}
     */
    public T writeOnly() {
        options.remove("readOnly");
        options.put("writeOnly", true);
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

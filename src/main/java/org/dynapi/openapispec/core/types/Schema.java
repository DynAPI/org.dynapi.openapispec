package org.dynapi.openapispec.core.types;

import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
     * set an example to this schema
     * @param example one or more
     */
    public T example(@NonNull Object example) {
        options.remove("examples");
        options.put("example", example);
        return getThis();
    }

    /**
     * adds an example to this schema
     * @param name distinct example name
     * @param example example value
     */
    public T addExample(@NonNull String name, Object example) {
        return addExample(name, options, null);
    }
    /**
     * adds an example to this schema
     * @param name distinct example name
     * @param example example value
     * @param summary short description for this example
     */
    public T addExample(@NonNull String name, Object example, String summary) {
        options.remove("example");  // remove single example
        JSONObject examples = (JSONObject) options.getOrDefault("examples", new JSONObject());
        options.put("examples", examples);
        JSONObject exampleObject = new JSONObject()
                .put("value", example);
        if (summary != null)
            exampleObject.put("summary", summary);
        examples.put(name, exampleObject);
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

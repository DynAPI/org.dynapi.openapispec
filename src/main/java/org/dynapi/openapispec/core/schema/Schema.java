package org.dynapi.openapispec.core.schema;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@ToString
@EqualsAndHashCode
@SuppressWarnings("unchecked")
public abstract class Schema<THIS extends Schema<THIS, ?>, TYPE> implements OpenApiSpecAble {
    protected final Map<String, Object> options = new HashMap<>();

    protected THIS getThis() { return (THIS) this; }

    /**
     * @param description schema description
     */
    public THIS description(@NonNull String description) {
        options.put("description", description);
        return getThis();
    }

    /**
     * @param value default-value if absent
     */
    public THIS defaultValue(TYPE value) {
        options.put("default", value);
        return getThis();
    }

    /**
     * marks that this field can be {@code null}
     */
    public THIS nullable() { return nullable(true); };
    /**
     * marks that this field can be {@code null}
     */
    public THIS nullable(boolean isNullable) {
        options.put("nullable", isNullable);
        return getThis();
    }

    /**
     * set an example to this schema
     * @param example one or more
     */
    public THIS example(@NonNull TYPE example) {
        return example(example, null);
    }

    /**
     * set an example to this schema
     * @param example one or more
     */
    public THIS example(@NonNull TYPE example, String summary) {
        return addExample("default", example, summary);
    }

    /**
     * adds an example to this schema
     * @param name distinct example name
     * @param example example value
     */
    public THIS addExample(@NonNull String name, TYPE example) {
        return addExample(name, example, null);
    }

    /**
     * adds an example to this schema
     * @param name distinct example name
     * @param example example value
     * @param summary short description for this example
     */
    public THIS addExample(@NonNull String name, TYPE example, String summary) {
        if (!this.options.containsKey("examples"))
            this.options.put("examples", new JSONObject());
        JSONObject examples = (JSONObject) this.options.get("examples");

        JSONObject exampleObject = new JSONObject()
                .put("value", example)
                .put("summary", summary);
        examples.put(name, exampleObject);
        return getThis();
    }

    /**
     * specify the only allowed options
     * @param options options that are allowed
     */
    public THIS options(@NonNull Object... options) {
        this.options.put("enum", new JSONArray(options));
        return getThis();
    }

    /**
     * marks this property as only used in GET (not POST/PUT/PATCH)<br>
     * note: only for direct properties of {@code TObject}
     */
    public THIS readOnly() {
        options.remove("writeOnly");
        this.options.put("readOnly", true);
        return getThis();
    }

    /**
     * marks this property as only used in POST/PUT/PATCH (not GET)<br>
     * note: only for direct properties of {@code TObject}
     */
    public THIS writeOnly() {
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

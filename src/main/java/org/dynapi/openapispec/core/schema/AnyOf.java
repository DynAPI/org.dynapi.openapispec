package org.dynapi.openapispec.core.schema;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * (OR) <br>
 * Must be valid against <b>any</b> of the subschemas
 */
public class AnyOf extends Schema<AnyOf, Void> {
    private final List<Schema<?, ?>> subSchemas = new ArrayList<>();

    public AnyOf(Schema<?, ?>... schemas) {
        subSchemas.addAll(List.of(schemas));
    }

    public AnyOf addSchema(Schema<?, ?> schema) {
        subSchemas.add(schema);
        return this;
    }

    @Override
    protected JSONObject finalized() {
        return new JSONObject()
                .put("anyOf", new JSONArray(subSchemas.stream().map(Schema::getOpenApiSpec).toList()));
    }

    @Override
    public AnyOf copy() {
        AnyOf copy = new AnyOf();
        copy.options.putAll(this.options);
        copy.subSchemas.addAll(this.subSchemas);
        return copy;
    }
}

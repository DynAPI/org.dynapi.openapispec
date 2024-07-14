package org.dynapi.openapispec.core;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.types.Schema;
import org.json.JSONObject;

@Builder
@ToString
public class Parameter implements OpenApiSpecAble {
    @NonNull
    private String name;
    private String description;
    @NonNull
    private Schema<?> schema;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("name", name)
                .put("description", description)
                .put("schema", schema.getOpenApiSpec());
    }
}

package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.dynapi.openapispec.core.types.Schema;
import org.json.JSONObject;

import java.util.Map;

@ToString
@Builder
public class MediaType implements OpenApiSpecAble {
    @NonNull
    private final Schema<?, ?> schema;
    private final Schema<?, ?> example;
    private final Map<String, Schema<?, ?>> examples;
//    private final Map<String, Encoding> encoding;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("schema", schema.getOpenApiSpec())
                .put("example", Utils.getOpenApiSpec(example))
                .put("examples", Utils.mapOpenApiSpecAble(examples));
    }
}

package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

import java.util.Map;

@ToString
@Builder(toBuilder = true)
public class Response implements OpenApiSpecAble {
    @NonNull
    private final String description;
    private final Map<String, Header> headers;
    private final Map<String, MediaType> content;
//    private final Map<String, Link> links;


    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("description", description)
                .put("headers", Utils.mapOpenApiSpecAble(headers))
                .put("content", Utils.mapOpenApiSpecAble(content));
    }
}

package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

import java.util.Map;

@ToString
@Builder(toBuilder = true)
public class RequestBody implements OpenApiSpecAble {
    public final String description;
    public final Map<String, MediaType> content;
    public final Boolean required;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("description", description)
                .put("content", Utils.mapOpenApiSpecAble(content))
                .put("required", required);
    }
}

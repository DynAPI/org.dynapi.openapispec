package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONObject;

@ToString
@Builder(toBuilder = true)
public class ExternalDocumentation implements OpenApiSpecAble {
    private final String description;
    @NonNull
    private final String url;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("description", description)
                .put("url", url);
    }
}

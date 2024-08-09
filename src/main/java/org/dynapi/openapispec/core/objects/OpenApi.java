package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONObject;

import java.util.Map;

@ToString
@Builder(toBuilder = true)
public class OpenApi implements OpenApiSpecAble {
    @NonNull
    public final String openapiVersion = "3.0.0";
    @NonNull
    public final Info info;
    public final Server[] servers;
    public final Components components;
    public final Map<String, String[]>[] security;
    public final Tag[] tags;
    public final ExternalDocumentation externalDocs;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("openapiVersion", openapiVersion)
                .put("info", info)
                .put("servers", servers)
                .put("components", components)
                .put("security", security)
                .put("tags", tags)
                .put("externalDocs", externalDocs);
    }
}

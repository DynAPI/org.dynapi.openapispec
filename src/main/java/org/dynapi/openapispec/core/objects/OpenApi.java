package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

@ToString
@Builder(toBuilder = true)
public class OpenApi implements OpenApiSpecAble {
    @NonNull
    public final String openapiVersion;
    @NonNull
    public final Info info;
    public final List<Server> servers;
    public final Map<String, Path> paths;
    public final Components components;
    public final Map<String, String[]>[] security;
    public final List<Tag> tags;
    public final ExternalDocumentation externalDocs;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("openapiVersion", openapiVersion)
                .put("info", info)
                .put("servers", servers)
                .put("paths", paths)
                .put("components", components)
                .put("security", security)
                .put("tags", tags)
                .put("externalDocs", externalDocs);
    }
}

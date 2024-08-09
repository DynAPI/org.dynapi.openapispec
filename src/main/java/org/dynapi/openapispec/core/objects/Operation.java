package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

@ToString
@Builder(toBuilder = true)
public class Operation implements OpenApiSpecAble {
    public final List<String> tags;
    public final String summary;
    public final String description;
    public final ExternalDocumentation externalDocs;
    public final String operationId;
    public final List<Parameter> parameters;
    public final RequestBody requestBody;
    @NonNull
    public final Map<String, Response> responses;
//    public final Map<String, Callback> callbacks;
    public final Boolean deprecated;
    public final Map<String, String[]>[] security;
    public final List<Server> servers;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("tags", tags)
                .put("summary", summary)
                .put("description", description)
                .put("externalDocs", Utils.getOpenApiSpec(externalDocs))
                .put("operationId", operationId)
                .put("parameters", Utils.mapOpenApiSpecAble(parameters))
                .put("requestBody", Utils.getOpenApiSpec(requestBody))
                .put("responses", Utils.mapOpenApiSpecAble(responses))
                .put("deprecated", deprecated)
                .put("security", security)
                .put("servers", Utils.mapOpenApiSpecAble(servers));
    }
}

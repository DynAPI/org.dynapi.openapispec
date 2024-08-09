package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

@ToString
@Builder(toBuilder = true)
public class Path implements OpenApiSpecAble {
    public final String summary;
    public final String description;
    public final Operation get;
    public final Operation put;
    public final Operation post;
    public final Operation delete;
    public final Operation options;
    public final Operation head;
    public final Operation patch;
    public final Operation trace;
    public final Server[] servers;
    public final Parameter[] parameters;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("summary", summary)
                .put("description", description)
                .put("get", Utils.getOpenApiSpec(get))
                .put("get", Utils.getOpenApiSpec(put))
                .put("get", Utils.getOpenApiSpec(post))
                .put("get", Utils.getOpenApiSpec(delete))
                .put("get", Utils.getOpenApiSpec(options))
                .put("get", Utils.getOpenApiSpec(head))
                .put("get", Utils.getOpenApiSpec(patch))
                .put("get", Utils.getOpenApiSpec(trace))
                .put("servers", Utils.mapOpenApiSpecAble(servers))
                .put("parameters", Utils.mapOpenApiSpecAble(parameters));
    }
}

package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

import java.util.List;

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
    public final List<Server> servers;
    public final List<Parameter> parameters;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("summary", summary)
                .put("description", description)
                .put("get", Utils.getOpenApiSpec(get))
                .put("put", Utils.getOpenApiSpec(put))
                .put("post", Utils.getOpenApiSpec(post))
                .put("delete", Utils.getOpenApiSpec(delete))
                .put("options", Utils.getOpenApiSpec(options))
                .put("head", Utils.getOpenApiSpec(head))
                .put("patch", Utils.getOpenApiSpec(patch))
                .put("trace", Utils.getOpenApiSpec(trace))
                .put("servers", Utils.mapOpenApiSpecAble(servers))
                .put("parameters", Utils.mapOpenApiSpecAble(parameters));
    }
}

package org.dynapi.openapispec.core;

import lombok.NonNull;
import org.dynapi.openapispec.core.types.Schema;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PathSchema implements OpenApiSpecAble {
    private String summary = null;
    private final List<String> tags = new ArrayList<>();
    private final List<JSONObject> parameters = new ArrayList<>();
    private JSONObject body = null;
    private final JSONObject responses = new JSONObject();

    public PathSchema summary(String summary) {
        this.summary = summary;
        return this;
    }

    public PathSchema addTag(@NonNull String tag) {
        tags.add(tag);
        return this;
    }

    public PathSchema addPathParameter(@NonNull Parameter parameter) {
        parameters.add(new JSONObject(parameter.getOpenApiSpec().toMap())
                .put("in", "path")
        );
        return this;
    }

    public PathSchema addQueryParameter(@NonNull Parameter parameter) {
        parameters.add(new JSONObject(parameter.getOpenApiSpec().toMap())
                .put("in", "query")
        );
        return this;
    }

    public PathSchema body(@NonNull Schema<?> body) {
        return body(body, "application/json");
    }
    public PathSchema body(@NonNull Schema<?> body, String contentType) {
        this.body = new JSONObject()
                .put("content", new JSONObject()
                        .put(contentType, new JSONObject()
                                .put("schema", body.getOpenApiSpec())
                        )
                );
        return this;
    }

    public PathSchema addResponse(int statusCode, @NonNull Schema<?> response) {
        return addResponse(statusCode, response, "application/json");
    }
    public PathSchema addResponse(int statusCode, @NonNull Schema<?> response, @NonNull String contentType) {
        responses.put(
                String.valueOf(statusCode),
                new JSONObject()
                        .put("description", Utils.statusCodeToText(statusCode))
                        .put("content", new JSONObject()
                                .put(contentType, response.getOpenApiSpec())
                        )
        );
        return this;
    }

    @Override
    public JSONObject getOpenApiSpec() {
        JSONObject spec = new JSONObject();
        spec.put("tags", tags);
        if (summary != null)
            spec.put("summary", summary);
        if (!parameters.isEmpty())
            spec.put("parameters", parameters);
        if (body != null)
            spec.put("requestBody", body);
        if (!responses.isEmpty())
            spec.put("responses", responses);
        return spec;
    }
}

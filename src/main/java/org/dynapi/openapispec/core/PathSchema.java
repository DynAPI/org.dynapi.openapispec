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

    /**
     * note: tag description is set on the OpenApiSpecBuilder
     * @param tag tag-name
     */
    public PathSchema addTag(@NonNull String tag) {
        tags.add(tag);
        return this;
    }

    /**
     * @param parameter path-parameter
     */
    public PathSchema addPathParameter(@NonNull Parameter parameter) {
        parameters.add(new JSONObject(parameter.getOpenApiSpec().toMap())
                .put("in", "path")
        );
        return this;
    }

    /**
     * @param parameter query-parameter
     */
    public PathSchema addQueryParameter(@NonNull Parameter parameter) {
        parameters.add(new JSONObject(parameter.getOpenApiSpec().toMap())
                .put("in", "query")
        );
        return this;
    }

    /**
     * adds a request-body (in e.g. POST or PUT)
     * @param body body schema
     */
    public PathSchema body(@NonNull Schema<?> body) {
        return body(body, "application/json");
    }

    /**
     * adds a request-body (in e.g. POST or PUT)
     * @param body body schema
     * @param contentType content-type of the body
     */
    public PathSchema body(@NonNull Schema<?> body, String contentType) {
        this.body = new JSONObject()
                .put("content", new JSONObject()
                        .put(contentType, new JSONObject()
                                .put("schema", body.getOpenApiSpec())
                        )
                );
        return this;
    }

    /**
     * @param statusCode http-status-code
     * @param response response-schema
     */
    public PathSchema addResponse(int statusCode, @NonNull Schema<?> response) {
        return addResponse(statusCode, response, "application/json");
    }

    /**
     * @param statusCode http-status-code
     * @param response response-schema
     * @param contentType content-type of the response
     */
    public PathSchema addResponse(int statusCode, @NonNull Schema<?> response, @NonNull String contentType) {
        String code = String.valueOf(statusCode);
        if (responses.has(code)) {  // this code already exists. add addition content-type for the response
            responses.getJSONObject(code).getJSONObject("content")
                    .put(contentType, response.getOpenApiSpec());
        } else {
            responses.put(
                    code,
                    new JSONObject()
                            .put("description", Utils.statusCodeToText(statusCode))
                            .put("content", new JSONObject()
                                    .put(contentType, response.getOpenApiSpec())
                            )
            );
        }
        return this;
    }

    @Override
    public JSONObject getOpenApiSpec() {
        JSONObject spec = new JSONObject();
        if (!tags.isEmpty())
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

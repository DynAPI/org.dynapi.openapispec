package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.dynapi.openapispec.core.types.Schema;
import org.json.JSONObject;

import java.util.Map;

@ToString
@Builder(toBuilder = true)
public class Components implements OpenApiSpecAble {
    public final Map<String, Schema<?, ?>> schemas;
    public final Map<String, Response> responses;
    public final Map<String, Parameter> parameters;
    public final Map<String, Example> examples;
    public final Map<String, RequestBody> requestBodies;
    public final Map<String, Header> headers;
//    public final Map<String, SecurityScheme> securitySchemes;
//    public final Map<String, Link> links;
//    public final Map<String, Callback> callbacks;


    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("schemas", Utils.mapOpenApiSpecAble(schemas))
                .put("responses", Utils.mapOpenApiSpecAble(responses))
                .put("parameters", Utils.mapOpenApiSpecAble(parameters))
                .put("examples", Utils.mapOpenApiSpecAble(examples))
                .put("requestBodies", Utils.mapOpenApiSpecAble(requestBodies))
                .put("headers", Utils.mapOpenApiSpecAble(headers));
    }
}

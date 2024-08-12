package org.dynapi.openapispec.core.objects;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.dynapi.openapispec.core.types.Schema;
import org.json.JSONObject;

import java.util.Map;

@With
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Components implements OpenApiSpecAble {
    /** An object to hold reusable {@code Schema} Objects. */
    public final Map<String, Schema<?, ?>> schemas;
    /** An object to hold reusable {@code Response} Objects. */
    public final Map<String, Response> responses;
    /** An object to hold reusable {@code Parameter} Objects. */
    public final Map<String, Parameter> parameters;
    /** An object to hold reusable {@code Example} Objects. */
    public final Map<String, Example> examples;
    /** An object to hold reusable {@code RequestBody} Objects. */
    public final Map<String, RequestBody> requestBodies;
    /** An object to hold reusable {@code Header} Objects. */
    public final Map<String, Header> headers;
    /** An object to hold reusable {@code SecurityScheme} Objects. */
    public final Map<String, SecurityScheme> securitySchemes;
//    /** An object to hold reusable {@code Link} Objects. */
//    public final Map<String, Link> links;
//    /** An object to hold reusable {@code Callback} Objects. */
//    public final Map<String, Callback> callbacks;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("schemas", Utils.mapOpenApiSpecAble2Json(schemas))
                .put("responses", Utils.mapOpenApiSpecAble2Json(responses))
                .put("parameters", Utils.mapOpenApiSpecAble2Json(parameters))
                .put("examples", Utils.mapOpenApiSpecAble2Json(examples))
                .put("requestBodies", Utils.mapOpenApiSpecAble2Json(requestBodies))
                .put("headers", Utils.mapOpenApiSpecAble2Json(headers))
                .put("securitySchemes", Utils.mapOpenApiSpecAble2Json(securitySchemes));
//                .put("links", Utils.mapOpenApiSpecAble(links));
//                .put("callbacks", Utils.mapOpenApiSpecAble(callbacks));
    }
}

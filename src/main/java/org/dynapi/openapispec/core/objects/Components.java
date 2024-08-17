package org.dynapi.openapispec.core.objects;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.dynapi.openapispec.core.types.Schema;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@With
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Components implements OpenApiSpecAble {
    /** An object to hold reusable {@link Schema} Objects. */
    @Builder.Default
    public final Map<String, Schema<?, ?>> schemas = new HashMap<>();
    /** An object to hold reusable {@link Response} Objects. */
    @Builder.Default
    public final Map<String, Response> responses = new HashMap<>();
    /** An object to hold reusable {@link Parameter} Objects. */
    @Builder.Default
    public final Map<String, Parameter> parameters = new HashMap<>();
    /** An object to hold reusable {@link Example} Objects. */
    @Builder.Default
    public final Map<String, Example> examples = new HashMap<>();
    /** An object to hold reusable {@link RequestBody} Objects. */
    @Builder.Default
    public final Map<String, RequestBody> requestBodies = new HashMap<>();
    /** An object to hold reusable {@link Header} Objects. */
    @Builder.Default
    public final Map<String, Header> headers = new HashMap<>();
    /** An object to hold reusable {@link SecurityScheme} Objects. */
    @Builder.Default
    public final Map<String, SecurityScheme> securitySchemes = new HashMap<>();
    /** An object to hold reusable {@link Link} Objects. */
    public final Map<String, Link> links = new HashMap<>();
//    /** An object to hold reusable {@code Callback} Objects. */
//    public final Map<String, Map<String, PathItem>> callbacks = new HashMap<>();
    /** An object to hold reusable {@link PathItem} Item Object. */
    public final Map<String, PathItem> pathItems = new HashMap<>();

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("schemas", Utils.mapOpenApiSpecAble2Json(schemas))
                .put("responses", Utils.mapOpenApiSpecAble2Json(responses))
                .put("parameters", Utils.mapOpenApiSpecAble2Json(parameters))
                .put("examples", Utils.mapOpenApiSpecAble2Json(examples))
                .put("requestBodies", Utils.mapOpenApiSpecAble2Json(requestBodies))
                .put("headers", Utils.mapOpenApiSpecAble2Json(headers))
                .put("securitySchemes", Utils.mapOpenApiSpecAble2Json(securitySchemes))
//                .put("callbacks", Utils.mapCallbacks(callbacks))
                .put("pathItems", Utils.mapOpenApiSpecAble2Json(pathItems));
    }
}

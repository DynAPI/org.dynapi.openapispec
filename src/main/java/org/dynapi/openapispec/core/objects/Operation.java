package org.dynapi.openapispec.core.objects;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

@With
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Operation implements OpenApiSpecAble {
    /** A list of tags for API documentation control.
     * Tags can be used for logical grouping of operations by resources or any other qualifier. */
    public final List<String> tags;
    /** A short summary of what the operation does. */
    public final String summary;
    /** A verbose explanation of the operation behavior. CommonMark syntax MAY be used for rich text representation. */
    public final String description;
    /** Additional external documentation for this operation. */
    public final ExternalDocumentation externalDocs;
    /** Unique string used to identify the operation.
     * The id MUST be unique among all operations described in the API.
     * The operationId value is case-sensitive.
     * Tools and libraries MAY use the operationId to uniquely identify an operation, therefore, it is RECOMMENDED to follow common programming naming conventions. */
    public final String operationId;
    /** A list of parameters that are applicable for this operation.
     * If a parameter is already defined at the {@code Path} Item, the new definition will override it but can never remove it.
     * The list MUST NOT include duplicated parameters.
     * A unique parameter is defined by a combination of a {@code name} and {@code location}.
     * The list can use the {@code Reference} Object to link to parameters that are defined at the OpenAPI Object's {@code components}/{@code parameters}. */
    public final List<Parameter> parameters;
    /** The request body applicable for this operation.
     * The {@code requestBody} is only supported in HTTP methods where the HTTP 1.1 specification RFC7231 has explicitly defined semantics for request bodies.
     * In other cases where the HTTP spec is vague, {@code requestBody} SHALL be ignored by consumers. */
    public final RequestBody requestBody;
    /** The list of possible responses as they are returned from executing this operation. */
    @NonNull
    public final Map<String, Response> responses;
//    /** A map of possible out-of band callbacks related to the parent operation.
//     * The key is a unique identifier for the Callback Object.
//     * Each value in the map is a {@code Callback} Object that describes a request that may be initiated by the API provider and the expected responses. */
//    public final Map<String, Callback> callbacks;
    /** Declares this operation to be deprecated. Consumers SHOULD refrain from usage of the declared operation. */
    public final Boolean deprecated;
    /** A declaration of which security mechanisms can be used for this operation.
     * The list of values includes alternative security requirement objects that can be used.
     * Only one of the security requirement objects need to be satisfied to authorize a request.
     * To make security optional, an empty security requirement ({@code {}}) can be included in the array.
     * This definition overrides any declared top-level security.
     * To remove a top-level security declaration, an empty array can be used. */
    public final Map<String, String[]>[] security;
    /** An alternative server array to service this operation.
     * If an alternative server object is specified at the Path Item Object or Root level, it will be overridden by this value. */
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

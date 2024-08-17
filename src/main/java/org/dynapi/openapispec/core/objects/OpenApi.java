package org.dynapi.openapispec.core.objects;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

@With
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class OpenApi implements OpenApiSpecAble {
    /** This string MUST be the semantic version number of the OpenAPI Specification version that the OpenAPI document uses.
     * The openapi field SHOULD be used by tooling specifications and clients to interpret the OpenAPI document.
     * This is not related to the API {@code info.version} string. */
    @NonNull
    public final String openapiVersion = "3.1.0";
    /** Provides metadata about the API. The metadata MAY be used by tooling as required. */
    @NonNull
    public final Info info;
    /** The default value for the $schema keyword within Schema Objects contained within this OAS document. This MUST be in the form of a URI. */
    public final String jsonSchemaDialect;
    /** An array of Server Objects, which provide connectivity information to a target server.
     * If the servers property is not provided, or is an empty array, the default value would be a Server Object with a url value of {@code /}. */
    public final List<Server> servers;
    /** The available paths and operations for the API. */
    public final Map<String, PathItem> paths;
    /** The incoming webhooks that MAY be received as part of this API and that the API consumer MAY choose to implement.
     * Closely related to the {@code callbacks} feature, this section describes requests initiated other than by an API call, for example by an out of band registration.
     * The key name is a unique string to refer to each webhook, while the (optionally referenced) Path Item Object describes a request that may be initiated by the API provider and the expected responses. */
    public final Map<String, PathItem> webhooks;
    /** An element to hold various schemas for the specification. */
    public final Components components;
    /** A declaration of which security mechanisms can be used across the API.
     * The list of values includes alternative security requirement objects that can be used.
     * Only one of the security requirement objects need to be satisfied to authorize a request.
     * Individual operations can override this definition. To make security optional, an empty security requirement ({@code {}}) can be included in the array. */
    public final List<Map<String, List<String>>> security;
    /** A list of tags used by the specification with additional metadata.
     * The order of the tags can be used to reflect on their order by the parsing tools.
     * Not all tags that are used by the Operation Object must be declared.
     * The tags that are not declared MAY be organized randomly or based on the tools' logic.
     * Each tag name in the list MUST be unique. */
    public final List<Tag> tags;
    /** Additional external documentation. */
    public final ExternalDocumentation externalDocumentation;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("openapi", openapiVersion)
                .put("info", Utils.getOpenApiSpec(info))
                .put("jsonSchemaDialect", jsonSchemaDialect)
                .put("servers", Utils.mapOpenApiSpecAble2Json(servers))
                .put("paths", Utils.mapOpenApiSpecAble2Json(paths))
                .put("webhooks", Utils.mapOpenApiSpecAble2Json(webhooks))
                .put("components", Utils.getOpenApiSpec(components))
                .put("security", (security == null || security.isEmpty()) ? null : new JSONArray(security))
                .put("tags", Utils.mapOpenApiSpecAble2Json(tags))
                .put("externalDocs", Utils.getOpenApiSpec(externalDocumentation));
    }
}

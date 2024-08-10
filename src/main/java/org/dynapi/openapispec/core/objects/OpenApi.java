package org.dynapi.openapispec.core.objects;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
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
    public final String openapiVersion;
    /** Provides metadata about the API. The metadata MAY be used by tooling as required. */
    @NonNull
    public final Info info;
    /** An array of Server Objects, which provide connectivity information to a target server.
     * If the servers property is not provided, or is an empty array, the default value would be a Server Object with a url value of {@code /}. */
    public final List<Server> servers;
    /** The available paths and operations for the API. */
    public final Map<String, Path> paths;
    /** An element to hold various schemas for the specification. */
    public final Components components;
    /** A declaration of which security mechanisms can be used across the API.
     * The list of values includes alternative security requirement objects that can be used.
     * Only one of the security requirement objects need to be satisfied to authorize a request.
     * Individual operations can override this definition. To make security optional, an empty security requirement ({@code {}}) can be included in the array. */
    public final Map<String, String[]>[] security;
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
                .put("openapiVersion", openapiVersion)
                .put("info", info)
                .put("servers", servers)
                .put("paths", paths)
                .put("components", components)
                .put("security", security)
                .put("tags", tags)
                .put("externalDocs", externalDocumentation);
    }
}

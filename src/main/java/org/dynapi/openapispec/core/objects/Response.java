package org.dynapi.openapispec.core.objects;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

import java.util.Map;

@With
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Response implements OpenApiSpecAble {
    /** A short description of the response. CommonMark syntax MAY be used for rich text representation. */
    @NonNull
    public final String description;
    /** Maps a header name to its definition. RFC7230 states header names are case-insensitive.
     * If a response header is defined with the name {@code "Content-Type"}, it SHALL be ignored. */
    public final Map<String, Header> headers;
    /** A map containing descriptions of potential response payloads.
     * The key is a media type or media type range and the value describes it.
     * For responses that match multiple keys, only the most specific key is applicable. e.g. {@code text/plain} overrides {@code text/*}
     */
    public final Map<String, MediaType> content;
    /** A map of operations links that can be followed from the response.
     * The key of the map is a short name for the link, following the naming constraints of the names for Component Objects. */
    private final Map<String, Link> links;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("description", description)
                .put("headers", Utils.mapOpenApiSpecAble2Json(headers))
                .put("content", Utils.mapOpenApiSpecAble2Json(content))
                .put("links", Utils.mapOpenApiSpecAble2Json(links));
    }

    /**
     * @see org.dynapi.openapispec.OpenApiSpecBuilder#registerRefResponse(String, Response)
     * @see org.dynapi.openapispec.core.objects.Response
     */
    @ToString
    @EqualsAndHashCode(callSuper = true)
    public static class Ref extends Response implements OpenApiObjectRef {
        @NonNull
        private final String ref;

        public Ref(@NonNull String name) {
            // just ignore these fields
            super("", null, null, null);
            this.ref = name;
        }

        @Override
        public JSONObject getOpenApiSpec() {
            return new JSONObject()
                    .put("$ref", "#/components/responses/" + ref);
        }
    }
}

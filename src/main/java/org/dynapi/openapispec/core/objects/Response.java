package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

import java.util.Map;

@ToString
@Builder(toBuilder = true)
public class Response implements OpenApiSpecAble {
    /** A short description of the response. CommonMark syntax MAY be used for rich text representation. */
    @NonNull
    private final String description;
    /** Maps a header name to its definition. RFC7230 states header names are case-insensitive.
     * If a response header is defined with the name {@code "Content-Type"}, it SHALL be ignored. */
    private final Map<String, Header> headers;
    /** A map containing descriptions of potential response payloads.
     * The key is a media type or media type range and the value describes it.
     * For responses that match multiple keys, only the most specific key is applicable. e.g. {@code text/plain} overrides {@code text/*}
     */
    private final Map<String, MediaType> content;
//    /** A map of operations links that can be followed from the response.
//     * The key of the map is a short name for the link, following the naming constraints of the names for Component Objects. */
//    private final Map<String, Link> links;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("description", description)
                .put("headers", Utils.mapOpenApiSpecAble(headers))
                .put("content", Utils.mapOpenApiSpecAble(content));
    }
}

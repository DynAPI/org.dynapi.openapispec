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
public class RequestBody implements OpenApiSpecAble {
    /** A brief description of the request body. This could contain examples of use. CommonMark syntax MAY be used for rich text representation. */
    public final String description;
    /** The content of the request body.
     * The key is a media type or media type range and the value describes it.
     * For requests that match multiple keys, only the most specific key is applicable. e.g. text/plain overrides {@code text/*}
     */
    public final Map<String, MediaType> content;
    /** Determines if the request body is required in the request. */
    public final Boolean required;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("description", description)
                .put("content", Utils.mapOpenApiSpecAble2Json(content))
                .put("required", required);
    }

    /**
     * @see org.dynapi.openapispec.OpenApiSpecBuilder#registerRefRequestBody(String, RequestBody)
     * @see org.dynapi.openapispec.core.objects.RequestBody
     */
    @ToString
    @EqualsAndHashCode(callSuper = true)
    public static class Ref extends RequestBody implements OpenApiObjectRef {
        @NonNull
        private final String ref;

        public Ref(@NonNull String name) {
            // just ignore these fields
            super("", null, null);
            this.ref = name;
        }

        @Override
        public JSONObject getOpenApiSpec() {
            return new JSONObject()
                    .put("$ref", "#/components/responses/" + ref);
        }
    }
}

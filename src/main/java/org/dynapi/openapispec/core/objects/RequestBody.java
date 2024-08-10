package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

import java.util.Map;

@ToString
@Builder(toBuilder = true)
public class RequestBody implements OpenApiSpecAble {
    /** A brief description of the request body. This could contain examples of use. CommonMark syntax MAY be used for rich text representation. */
    public final String description;
    /** The content of the request body. The key is a media type or media type range and the value describes it. For requests that match multiple keys, only the most specific key is applicable. e.g. text/plain overrides {@code text/*} */
    public final Map<String, MediaType> content;
    /** Determines if the request body is required in the request. */
    public final Boolean required;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("description", description)
                .put("content", Utils.mapOpenApiSpecAble(content))
                .put("required", required);
    }
}

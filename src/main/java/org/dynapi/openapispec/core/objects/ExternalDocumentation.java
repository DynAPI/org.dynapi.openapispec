package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONObject;

@ToString
@Builder(toBuilder = true)
public class ExternalDocumentation implements OpenApiSpecAble {
    /** A short description of the target documentation. CommonMark syntax MAY be used for rich text representation. */
    private final String description;
    /** The URL for the target documentation. Value MUST be in the format of a URL. */
    @NonNull
    private final String url;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("description", description)
                .put("url", url);
    }
}

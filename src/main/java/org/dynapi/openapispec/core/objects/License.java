package org.dynapi.openapispec.core.objects;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONObject;

@With
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class License implements OpenApiSpecAble {
    /** The license name used for the API. */
    @NonNull
    public final String name;
    /** A URL to the license used for the API. MUST be in the format of a URL. */
    public final String url;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("name", name)
                .put("url", url);
    }
}

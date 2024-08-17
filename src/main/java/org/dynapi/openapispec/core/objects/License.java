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
    /** An SPDX license expression for the API. The {@code identifier} field is mutually exclusive of the {@code url} field. */
    public final String identifier;
    /** A URL to the license used for the API. This MUST be in the form of a URL. The {@code url} field is mutually exclusive of the {@code identifier} field. */
    public final String url;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("name", name)
                .put("identifier", identifier)
                .put("url", url);
    }
}

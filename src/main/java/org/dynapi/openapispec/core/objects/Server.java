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
public class Server implements OpenApiSpecAble {
    /** A URL to the target host.
     * This URL supports Server Variables and MAY be relative, to indicate that the host location is relative to the location where the OpenAPI document is being served.
     * Variable substitutions will be made when a variable is named in {@code {brackets}}. */
    @NonNull
    public final String url;
    /** An optional string describing the host designated by the URL. CommonMark syntax MAY be used for rich text representation. */
    public final String description;
    /** A map between a variable name and its value. The value is used for substitution in the server's URL template. */
    public final Map<String, ServerVariable> variables;


    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("url", url)
                .put("description", description)
                .put("variables", Utils.mapOpenApiSpecAble2Json(variables));
    }
}

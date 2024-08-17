package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.With;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

import java.util.Map;

@With
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Encoding implements OpenApiSpecAble {
    /** The Content-Type for encoding a specific property.
     * Default value depends on the property type: for object - {@code application/json}; for {@code array} – the default is defined based on the inner type; for all other cases the default is {@code application/octet-stream}. The value can be a specific media type (e.g. {@code application/json}), a wildcard media type (e.g. {@code image/*}), or a comma-separated list of the two types.
     */
    public final String contentType;
    /** A map allowing additional information to be provided as headers, for example {@code Content-Disposition}.
     * {@code Content-Type} is described separately and SHALL be ignored in this section.
     * This property SHALL be ignored if the request body media type is not a {@code multipart}. */
    public final Map<String, Header> headers;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("contentType", contentType)
                .put("headers", Utils.mapOpenApiSpecAble2Json(headers));
    }
}

package org.dynapi.openapispec.core.objects;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.dynapi.openapispec.core.types.Schema;
import org.json.JSONObject;

@With
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Example implements OpenApiSpecAble {
    /** Short description for the example. */
    private final String summary;
    /** Long description for the example. CommonMark syntax MAY be used for rich text representation. */
    private final String description;
    /** Embedded literal example.
     * The {@code value} field and {@code externalValue} field are mutually exclusive.
     * To represent examples of media types that cannot naturally represented in JSON or YAML, use a string value to contain the example, escaping where necessary. */
    private final Schema<?, ?> value;
    /** A URL that points to the literal example.
     * This provides the capability to reference examples that cannot easily be included in JSON or YAML documents.
     * The value {@code field} and {@code externalValue} field are mutually exclusive. */
    private final String externalValueUrl;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("summary", summary)
                .put("description", description)
                .put("value", Utils.getOpenApiSpec(value))
                .put("externalValue", externalValueUrl);
    }
}

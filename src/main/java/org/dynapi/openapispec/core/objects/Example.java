package org.dynapi.openapispec.core.objects;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONObject;

@With
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Example implements OpenApiSpecAble {
    /** Short description for the example. */
    public final String summary;
    /** Long description for the example. CommonMark syntax MAY be used for rich text representation. */
    public final String description;
    /** Embedded literal example.
     * The {@code value} field and {@code externalValue} field are mutually exclusive.
     * To represent examples of media types that cannot naturally represented in JSON or YAML, use a string value to contain the example, escaping where necessary. */
    public final Object value;
    /** A URL that points to the literal example.
     * This provides the capability to reference examples that cannot easily be included in JSON or YAML documents.
     * The value {@code field} and {@code externalValue} field are mutually exclusive. */
    public final String externalValueUrl;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("summary", summary)
                .put("description", description)
                .put("value", value)
                .put("externalValue", externalValueUrl);
    }

    /**
     * @see org.dynapi.openapispec.OpenApiSpecBuilder#registerRefExample(String, Example)
     * @see org.dynapi.openapispec.core.objects.Example
     */
    @ToString
    @EqualsAndHashCode(callSuper = true)
    public static class Ref extends Example implements OpenApiObjectRef {
        @NonNull
        private final String ref;

        public Ref(@NonNull String name) {
            // just ignore these fields
            super(null, null, null, null);
            this.ref = name;
        }

        @Override
        public JSONObject getOpenApiSpec() {
            return new JSONObject()
                    .put("$ref", "#/components/examples/" + ref);
        }
    }
}

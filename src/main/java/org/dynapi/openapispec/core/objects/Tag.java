package org.dynapi.openapispec.core.objects;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

@With
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Tag implements OpenApiSpecAble {
    /** The name of the tag. */
    @NonNull
    public final String name;
    /** A short description for the tag. CommonMark syntax MAY be used for rich text representation. */
    public final String description;
    /** Additional external documentation for this tag. */
    public final ExternalDocumentation externalDocs;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("name", name)
                .put("description", description)
                .put("externalDocs", Utils.getOpenApiSpec(externalDocs));
    }
}

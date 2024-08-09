package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

@ToString
@Builder(toBuilder = true)
public class Tag implements OpenApiSpecAble {
    @NonNull
    private final String name;
    private final String description;
    private final ExternalDocumentation externalDocs;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("name", name)
                .put("description", description)
                .put("externalDocs", Utils.getOpenApiSpec(externalDocs));
    }
}

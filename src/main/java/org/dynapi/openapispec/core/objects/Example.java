package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.dynapi.openapispec.core.types.Schema;
import org.json.JSONObject;

@ToString
@Builder(toBuilder = true)
public class Example implements OpenApiSpecAble {
    private final String summary;
    private final String description;
    private final Schema<?, ?> value;
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

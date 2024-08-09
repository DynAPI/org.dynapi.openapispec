package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONObject;

@ToString
@Builder(toBuilder = true)
public class Server implements OpenApiSpecAble {
    @NonNull
    public final String url;
    public final String description;
//    public final Map<String, ServerVariable> variables;


    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("url", url)
                .put("description", description);
    }
}

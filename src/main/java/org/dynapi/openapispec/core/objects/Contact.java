package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONObject;

@ToString
@Builder(toBuilder = true)
public class Contact implements OpenApiSpecAble {
    public final String name;
    public final String url;
    public final String email;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("name", name)
                .put("url", url)
                .put("email", email);
    }
}

package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

@ToString
@Builder(toBuilder = true)
public class Info implements OpenApiSpecAble {
    @NonNull
    public final String title;
    public final String description;
    public final String termsOfServiceUrl;
    public final Contact contact;
    public final License license;
    @NonNull
    public final String version;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("title", title)
                .put("description", description)
                .put("termsOfService", termsOfServiceUrl)
                .put("contact", Utils.getOpenApiSpec(contact))
                .put("license", Utils.getOpenApiSpec(license))
                .put("version", version);
    }
}

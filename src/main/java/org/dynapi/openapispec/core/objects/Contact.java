package org.dynapi.openapispec.core.objects;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONObject;

@With
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Contact implements OpenApiSpecAble {
    /** The identifying name of the contact person/organization. */
    public final String name;
    /** The URL pointing to the contact information. MUST be in the format of a URL. */
    public final String url;
    /** The email address of the contact person/organization. MUST be in the format of an email address. */
    public final String email;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("name", name)
                .put("url", url)
                .put("email", email);
    }
}

package org.dynapi.openapispec.core.objects;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

@With
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Info implements OpenApiSpecAble {
    /** The title of the API. */
    @NonNull
    public final String title;
    /** A short summary of the API. */
    public final String summary;
    /** A short description of the API. CommonMark syntax MAY be used for rich text representation. */
    public final String description;
    /** A URL to the Terms of Service for the API. MUST be in the format of a URL. */
    public final String termsOfServiceUrl;
    /** The contact information for the exposed API. */
    public final Contact contact;
    /** The license information for the exposed API. */
    public final License license;
    /** The version of the OpenAPI document (which is distinct from the OpenAPI Specification version or the API implementation version). */
    @NonNull
    public final String version;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("title", title)
                .put("summary", summary)
                .put("description", description)
                .put("termsOfService", termsOfServiceUrl)
                .put("contact", Utils.getOpenApiSpec(contact))
                .put("license", Utils.getOpenApiSpec(license))
                .put("version", version);
    }
}

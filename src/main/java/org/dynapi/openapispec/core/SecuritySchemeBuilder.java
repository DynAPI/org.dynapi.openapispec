package org.dynapi.openapispec.core;

import lombok.NonNull;
import org.dynapi.openapispec.core.objects.SecurityScheme;

public class SecuritySchemeBuilder {

    public static SecurityScheme apiKey(@NonNull String in, @NonNull String headerName) {
        return apiKey(SecurityScheme.ApiKeyIn.valueOf(in), headerName);
    }

    public static SecurityScheme apiKey(@NonNull SecurityScheme.ApiKeyIn in, @NonNull String headerName) {
        return SecurityScheme.builder()
                .type(SecurityScheme.Type.API_KEY)
                .apiKeyIn(in)
                .apiKeyHeaderName(headerName)
                .build();
    }

    private static SecurityScheme.SecuritySchemeBuilder httpBuilder(@NonNull String scheme) {
        return SecurityScheme.builder()
                .type(SecurityScheme.Type.HTTP)
                .httpScheme(scheme);
    }

    public static SecurityScheme http(@NonNull String scheme) {
        return httpBuilder(scheme).build();
    }

    public static SecurityScheme httpBasic() {
        return httpBuilder("basic").build();
    }

    public static SecurityScheme httpDigest() {
        return httpBuilder("digest").build();
    }

    public static SecurityScheme httpBearer(@NonNull String bearerFormat) {
        return httpBuilder("bearer")
                .httpBearerFormat(bearerFormat)
                .build();
    }

    public static SecurityScheme openIdConnect(@NonNull String openIdConnectUrl) {
        return SecurityScheme.builder()
                .type(SecurityScheme.Type.OPEN_ID_CONNECT)
                .openIdConnectUrl(openIdConnectUrl)
                .build();
    }
}

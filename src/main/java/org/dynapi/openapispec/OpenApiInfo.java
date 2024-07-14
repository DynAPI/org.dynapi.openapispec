package org.dynapi.openapispec;

import lombok.Builder;

@Builder(toBuilder = true)
public class OpenApiInfo {
    public final String title;
    public final String version;
    public final String description;
    public final String logo;
}

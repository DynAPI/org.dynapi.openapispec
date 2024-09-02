package org.dynapi.openapispec.core;

import lombok.*;
import org.dynapi.openapispec.core.objects.MediaType;
import org.dynapi.openapispec.core.objects.RequestBody;
import org.dynapi.openapispec.core.schema.Schema;

import java.util.HashMap;
import java.util.Map;

/**
 * @see RequestBody
 */
@ToString
@EqualsAndHashCode
public class RequestBodyBuilder {
    private String description = null;
    private final Map<String, MediaType> content = new HashMap<>();
    private Boolean required = null;

    /**
     * @param description description of this Request-Body
     */
    public RequestBodyBuilder description(@NonNull String description) {
        this.description = description;
        return this;
    }

    /**
     * @param mimetype media type or media type range
     * @param contentSchema media description
     */
    public RequestBodyBuilder addContent(@NonNull String mimetype, @NonNull Schema<?, ?> contentSchema) {
        return addContent(mimetype, MediaType.builder().schema(contentSchema).build());
    }

    /**
     * @param mimetype media type or media type range
     * @param content media description
     */
    public RequestBodyBuilder addContent(@NonNull String mimetype, @NonNull MediaType content) {
        this.content.put(mimetype, content);
        return this;
    }

    public RequestBodyBuilder required(@NonNull Boolean required) {
        this.required = required;
        return this;
    }

    public RequestBody build() {
        return RequestBody.builder()
                .description(description)
                .content(content)
                .required(required)
                .build();
    }
}

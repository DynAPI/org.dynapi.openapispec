package org.dynapi.openapispec.core;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.dynapi.openapispec.core.objects.Header;
import org.dynapi.openapispec.core.objects.MediaType;
import org.dynapi.openapispec.core.objects.Response;
import org.dynapi.openapispec.core.schema.Schema;

import java.util.HashMap;
import java.util.Map;

/**
 * @see Response
 */
@ToString
@EqualsAndHashCode
public class ResponseBuilder {
    private String description = null;
    private final Map<String, Header> headers = new HashMap<>();
    private final Map<String, MediaType> content = new HashMap<>();

    /**
     * sets the description based of the status code
     * @param status http-status code
     */
    public ResponseBuilder descriptionForStatus(int status) {
        return description(Utils.statusCodeToText(status));
    }

    /**
     * @param description description of this response
     */
    public ResponseBuilder description(String description) {
        this.description = description;
        return this;
    }

    /**
     * @param name header name
     * @param header header definition
     */
    public ResponseBuilder addHeader(String name, Header header) {
        this.headers.put(name, header);
        return this;
    }

    /**
     * @param contentSchema media description
     */
    public ResponseBuilder addContent(Schema<?, ?> contentSchema) {
        return addContent("application/json", contentSchema);
    }

    /**
     * @param mimetype media type or media type range
     * @param contentSchema media description
     */
    public ResponseBuilder addContent(String mimetype, Schema<?, ?> contentSchema) {
        return addContent(mimetype, MediaType.builder().schema(contentSchema).build());
    }

    /**
     * @param content media description
     */
    public ResponseBuilder addContent(MediaType content) {
        return addContent("application/json", content);
    }

    /**
     * @param mimetype media type or media type range
     * @param content media description
     */
    public ResponseBuilder addContent(String mimetype, MediaType content) {
        this.content.put(mimetype, content);
        return this;
    }

    public Response build() {
        return Response.builder()
                .description(description)
                .headers(headers)
                .content(content)
                .build();
    }
}

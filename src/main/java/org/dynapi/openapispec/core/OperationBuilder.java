package org.dynapi.openapispec.core;

import lombok.*;
import org.dynapi.openapispec.core.objects.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see Operation
 */
@ToString
@EqualsAndHashCode
public class OperationBuilder {
    private final List<String> tags = new ArrayList<>();
    private String summary = null;
    private String description = null;
    private ExternalDocumentation externalDocumentation = null;
    private String operationId = null;
    private final List<Parameter> parameters = new ArrayList<>();
    private RequestBody requestBody = null;
    private final Map<String, Response> responses = new HashMap<>();
    private Boolean deprecated = null;
    private final List<Map<String, List<String>>> security = new ArrayList<>();
    private final List<Server> servers = new ArrayList<>();

    /**
     * note: tag description is set on the OpenApiSpecBuilder
     * @param tagName tag-name
     */
    public OperationBuilder addTag(@NonNull String tagName) {
        tags.add(tagName);
        return this;
    }

    /**
     * @param summary summary for this operation
     */
    public OperationBuilder summary(@NonNull String summary) {
        this.summary = summary;
        return this;
    }

    /**
     * @param description longer description for this operation
     */
    public OperationBuilder description(@NonNull String description) {
        this.description = description;
        return this;
    }

    /**
     * @param externalDocumentation external documentation for this operation
     */
    public OperationBuilder externalDocumentation(@NonNull ExternalDocumentation externalDocumentation) {
        this.externalDocumentation = externalDocumentation;
        return this;
    }

    /**
     * @param operationId unique operation ID
     */
    public OperationBuilder operationId(@NonNull String operationId) {
        this.operationId = operationId;
        return this;
    }

    /**
     * @param parameter parameter to add
     */
    public OperationBuilder addParameter(@NonNull Parameter parameter) {
        parameters.add(parameter);
        return this;
    }

    /**
     * adds a request-body (in e.g. POST or PUT)
     * @param requestBody body schema
     */
    public OperationBuilder requestBody(@NonNull RequestBody requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    /**
     * @param statusCode http-status-code
     * @param response response-schema
     */
    public OperationBuilder addResponse(int statusCode, @NonNull Response response) {
        this.responses.put(String.valueOf(statusCode), response);
        return this;
    }

    /**
     * marks this operation as deprecated
     */
    public OperationBuilder deprecated() {
        return deprecated(true);
    }

    /**
     * @param deprecated marks if the operation is deprecated
     */
    public OperationBuilder deprecated(@NonNull Boolean deprecated) {
        this.deprecated = deprecated;
        return this;
    }

    public OperationBuilder addSecurity(@NonNull Map<String, List<String>> security) {
        this.security.add(security);
        return this;
    }

    public OperationBuilder addServer(@NonNull Server server) {
        this.servers.add(server);
        return this;
    }

    public Operation build() {
        return Operation.builder()
                .tags(tags)
                .summary(summary)
                .description(description)
                .externalDocs(externalDocumentation)
                .operationId(operationId)
                .parameters(parameters)
                .requestBody(requestBody)
                .responses(responses)
                .deprecated(deprecated)
                .security(security)
                .servers(servers)
                .build();
    }
}

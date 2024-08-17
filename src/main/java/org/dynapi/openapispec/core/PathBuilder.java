package org.dynapi.openapispec.core;

import lombok.*;
import org.dynapi.openapispec.core.objects.Operation;
import org.dynapi.openapispec.core.objects.Parameter;
import org.dynapi.openapispec.core.objects.PathItem;
import org.dynapi.openapispec.core.objects.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see PathItem
 */
@ToString
@EqualsAndHashCode
public class PathBuilder {
    private String summary = null;
    private String description = null;
    private final Map<OperationType, Operation> methods = new HashMap<>();
    private final List<Server> servers = new ArrayList<>();
    private final List<Parameter> parameters = new ArrayList<>();

    /**
     * @param summary summary of this path
     */
    public PathBuilder summary(@NonNull String summary) {
        this.summary = summary;
        return this;
    }

    /**
     * @param description description of this path
     */
    public PathBuilder description(@NonNull String description) {
        this.description = description;
        return this;
    }

    /**
     * @param operationType HTTP-Method
     * @param operation operation specification
     */
    public PathBuilder addMethod(@NonNull OperationType operationType, @NonNull Operation operation) {
        methods.put(operationType, operation);
        return this;
    }

    /**
     * @param server adds a server that is available for this path
     */
    public PathBuilder addServer(@NonNull Server server) {
        servers.add(server);
        return this;
    }

    /**
     * @param parameter parameter that should be applied to all operations
     */
    public PathBuilder addCommonParameter(@NonNull Parameter parameter) {
        parameters.add(parameter);
        return this;
    }

    public PathItem build() {
        PathItem.PathItemBuilder builder = PathItem.builder()
                .summary(summary)
                .description(description)
                .servers(servers)
                .parameters(parameters);
        for (OperationType operationType : methods.keySet()) {
            switch (operationType) {
                case GET -> builder.get(methods.get(operationType));
                case PUT -> builder.put(methods.get(operationType));
                case POST -> builder.post(methods.get(operationType));
                case DELETE -> builder.delete(methods.get(operationType));
                case OPTIONS -> builder.options(methods.get(operationType));
                case HEAD -> builder.head(methods.get(operationType));
                case PATCH -> builder.patch(methods.get(operationType));
                case TRACE -> builder.trace(methods.get(operationType));
            }
        }
        return builder.build();
    }
}

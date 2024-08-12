package org.dynapi.openapispec.core;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.objects.Server;
import org.dynapi.openapispec.core.objects.ServerVariable;

import java.util.HashMap;
import java.util.Map;

@ToString
@EqualsAndHashCode
public class ServerBuilder {
    private String url = null;
    private String description = null;
    private final Map<String, ServerVariable> variables = new HashMap<>();

    public ServerBuilder url(@NonNull String url) {
        this.url = url;
        return this;
    }

    public ServerBuilder description(@NonNull String description) {
        this.description = description;
        return this;
    }

    public ServerBuilder addVariable(@NonNull String name, @NonNull ServerVariable variable) {
        this.variables.put(name, variable);
        return this;
    }

    public Server build() {
        return Server.builder()
                .url(url)
                .description(description)
                .variables(variables)
                .build();
    }
}

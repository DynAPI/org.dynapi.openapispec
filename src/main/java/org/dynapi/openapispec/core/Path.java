package org.dynapi.openapispec.core;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@ToString
public class Path implements OpenApiSpecAble {
    @Getter
    private final String path;
    private String summary = null;
    private String description = null;
    private boolean deprecated = false;
    private JSONObject externalDocs = null;
    private final Map<Operation, PathSchema> methods = new HashMap<>();

    public Path(String path) {
        this.path = path;
    }

    /**
     * @param summary summary of this path
     */
    public Path summary(@NonNull String summary) {
        this.summary = summary;
        return this;
    }

    /**
     * @param description description of this path
     */
    public Path description(@NonNull String description) {
        this.description = description;
        return this;
    }

    /**
     * marks this path as deprecated
     */
    public Path deprecated() {
        return deprecated(true);
    }
    /**
     * marks this path as deprecated
     */
    public Path deprecated(boolean deprecated) {
        this.deprecated = deprecated;
        return this;
    }

    /**
     * @param operation HTTP-Method
     * @param schema path-schema
     */
    public Path addMethod(@NonNull Operation operation, @NonNull PathSchema schema) {
        methods.put(operation, schema);
        return this;
    }

    /**
     * adds external documentation for further information
     * @param url external documentation
     */
    public Path externalDocs(@NonNull String url) {
        return externalDocs(url, null);
    }
    /**
     * adds external documentation for further information
     * @param url external documentation
     * @param description description for the external documentation
     */
    public Path externalDocs(@NonNull String url, String description) {
        externalDocs = new JSONObject()
                .put("url", url);
        if (description != null)
            externalDocs.put("description", description);
        return this;
    }

    @Override
    public JSONObject getOpenApiSpec() {
        JSONObject spec = new JSONObject();

        if (summary != null)
            spec.put("summary", summary);
        if (description != null)
            spec.put("description", description);
        if (deprecated)
            spec.put("deprecated", true);
        if (externalDocs != null)
            spec.put("externalDocs", externalDocs);

        for (var entry : methods.entrySet()) {
            spec.put(entry.getKey().value, entry.getValue().getOpenApiSpec());
        }
        return spec;
    }
}

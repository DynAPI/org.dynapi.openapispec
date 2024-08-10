package org.dynapi.openapispec.core;

import lombok.*;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@ToString
@EqualsAndHashCode
public class Path implements OpenApiSpecAble {
    @Getter
    private final String path;
    private String summary = null;
    private String description = null;
    private Boolean deprecated = null;
    private JSONObject externalDocs = null;
    private final Map<String, PathSchema> methods = new HashMap<>();

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
    public Path addMethod(@NonNull OperationType operation, @NonNull PathSchema schema) {
        methods.put(operation.value, schema);
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
        return new JSONObject()
                .put("summary", summary)
                .put("description", description)
                .put("deprecated", deprecated)
                .put("externalDocs", externalDocs)
                .put("methods", Utils.mapOpenApiSpecAble(methods));
    }
}

package org.dynapi.openapispec.core;

import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class Path implements OpenApiSpecAble {
    private final String path;
    private final Map<String, PathSchema> methods = new HashMap<>();

    public Path(String path) {
        this.path = path;
    }

    public Path addMethod(String method, PathSchema schema) {
        methods.put(method, schema);
        return this;
    }

    @Override
    public JSONObject getOpenApiSpec() {
        JSONObject spec = new JSONObject();
        for (Map.Entry<String, PathSchema> entry : methods.entrySet()) {
            spec.put(entry.getKey(), entry.getValue().getOpenApiSpec());
        }
        return spec;
    }
}

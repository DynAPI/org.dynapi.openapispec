package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.dynapi.openapispec.core.types.Schema;
import org.json.JSONObject;

import java.util.Map;

@Builder
@ToString
public class Parameter implements OpenApiSpecAble {
    @NonNull
    private final String name;
    @NonNull
    private final In in;
    private final String description;
    private final Boolean required;
    private final Boolean deprecated;
    private final Schema<?, ?> schema;
    private final Schema<?, ?> example;
    private final Map<String, Example> examples;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("name", name)
                .put("in", in.value)
                .put("description", description)
                .put("required", required)
                .put("deprecated", deprecated)
                .put("schema", Utils.getOpenApiSpec(schema))
                .put("example", Utils.getOpenApiSpec(example))
                .put("examples", Utils.mapOpenApiSpecAble(examples));
    }

    public enum In {
        PATH("path"),
        QUERY("query"),
        HEADER("header"),
        COOKIE("cookie"),
        ;

        public final String value;

        In(String value) {
            this.value = value;
        }
    }
}

package org.dynapi.openapispec.core.objects;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.dynapi.openapispec.core.types.Schema;
import org.json.JSONObject;

import java.util.Map;

@With
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Parameter implements OpenApiSpecAble {
    /**
     * The name of the parameter. Parameter names are case-sensitive.
     * <ul>
     *     <li>If in is "path", the name field MUST correspond to a template expression occurring within the path field in the Paths Object. See Path Templating for further information.</li>
     *     <li>If in is "header" and the name field is "Accept", "Content-Type" or "Authorization", the parameter definition SHALL be ignored.</li>
     *     <li>For all other cases, the name corresponds to the parameter name used by the in property.</li>
     * </ul>
     */
    @NonNull
    public final String name;
    /** The location of the parameter. Possible values are {@code "query"}, {@code "header"}, {@code "path"} or {@code "cookie"}. */
    @NonNull
    public final In in;
    /** A brief description of the parameter. This could contain examples of use. CommonMark syntax MAY be used for rich text representation. */
    public final String description;
    /** Determines whether this parameter is mandatory.
     * If the parameter location is "path", this property is REQUIRED and its value MUST be true.
     * Otherwise, the property MAY be included and its default value is false. */
    public final Boolean required;
    /** Specifies that a parameter is deprecated and SHOULD be transitioned out of usage. Default value is false. */
    public final Boolean deprecated;
    /** The schema defining the type used for the parameter. */
    public final Schema<?, ?> schema;
    /** Example of the parameter's potential value.
     * The example SHOULD match the specified schema and encoding properties if present.
     * The example field is mutually exclusive of the examples field.
     * Furthermore, if referencing a schema that contains an example, the example value SHALL override the example provided by the schema.
     * To represent examples of media types that cannot naturally be represented in JSON or YAML, a string value can contain the example with escaping where necessary. */
    public final Object example;
    /** Examples of the parameter's potential value.
     * Each example SHOULD contain a value in the correct format as specified in the parameter encoding.
     * The examples field is mutually exclusive of the example field.
     * Furthermore, if referencing a schema that contains an example, the examples value SHALL override the example provided by the schema. */
    public final Map<String, Example> examples;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("name", name)
                .put("in", in.value)
                .put("description", description)
                .put("required", required)
                .put("deprecated", deprecated)
                .put("schema", Utils.getOpenApiSpec(schema))
                .put("example", example)
                .put("examples", Utils.mapOpenApiSpecAble2Json(examples));
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

    /**
     * @see org.dynapi.openapispec.OpenApiSpecBuilder#registerRefParameter(String, Parameter)
     * @see org.dynapi.openapispec.core.objects.Parameter
     */
    @ToString
    @EqualsAndHashCode(callSuper = true)
    public static class Ref extends Parameter implements OpenApiObjectRef {
        @NonNull
        private final String ref;

        public Ref(@NonNull String name) {
            // just ignore these fields
            super("", In.COOKIE, null, null, null, null, null, null);
            this.ref = name;
        }

        @Override
        public JSONObject getOpenApiSpec() {
            return new JSONObject()
                    .put("$ref", "#/components/parameters/" + ref);
        }
    }
}

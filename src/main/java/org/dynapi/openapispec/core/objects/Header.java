package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.dynapi.openapispec.core.types.Schema;
import org.json.JSONObject;

import java.util.Map;

@ToString
@Builder(toBuilder = true)
public class Header implements OpenApiSpecAble {
    /** A brief description of the parameter. This could contain examples of use. CommonMark syntax MAY be used for rich text representation. */
    private final String description;
    /** Determines whether this parameter is mandatory.
     * If the parameter location is "path", this property is REQUIRED and its value MUST be true.
     * Otherwise, the property MAY be included and its default value is false. */
    private final Boolean required;
    /** Specifies that a parameter is deprecated and SHOULD be transitioned out of usage. Default value is false. */
    private final Boolean deprecated;
    /** The schema defining the type used for the parameter. */
    private final Schema<?, ?> schema;
    /** Example of the parameter's potential value.
     * The example SHOULD match the specified schema and encoding properties if present.
     * The example field is mutually exclusive of the examples field.
     * Furthermore, if referencing a schema that contains an example, the example value SHALL override the example provided by the schema.
     * To represent examples of media types that cannot naturally be represented in JSON or YAML, a string value can contain the example with escaping where necessary. */
    private final Schema<?, ?> example;
    /** Examples of the parameter's potential value.
     * Each example SHOULD contain a value in the correct format as specified in the parameter encoding.
     * The examples field is mutually exclusive of the example field.
     * Furthermore, if referencing a schema that contains an example, the examples value SHALL override the example provided by the schema. */
    private final Map<String, Example> examples;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("description", description)
                .put("required", required)
                .put("deprecated", deprecated)
                .put("schema", Utils.getOpenApiSpec(schema))
                .put("example", Utils.getOpenApiSpec(example))
                .put("examples", Utils.mapOpenApiSpecAble(examples));
    }
}

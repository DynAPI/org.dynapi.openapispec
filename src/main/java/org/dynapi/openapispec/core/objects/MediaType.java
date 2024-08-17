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
public class MediaType implements OpenApiSpecAble {
    /** The schema defining the content of the request, response, or parameter. */
    @NonNull
    public final Schema<?, ?> schema;
    /** Example of the media type.
     * The example object SHOULD be in the correct format as specified by the media type.
     * The example field is mutually exclusive of the examples field.
     * Furthermore, if referencing a schema which contains an example, the example value SHALL override the example provided by the schema. */
    public final Object example;
    /** Examples of the media type.
     * Each example object SHOULD match the media type and specified schema if present.
     * The examples field is mutually exclusive of the example field.
     * Furthermore, if referencing a schema which contains an example, the examples value SHALL override the example provided by the schema. */
    public final Map<String, Example> examples;
    /** A map between a property name and its encoding information.
     * The key, being the property name, MUST exist in the schema as a property.
     * The encoding object SHALL only apply to requestBody objects when the media type is multipart or application/x-www-form-urlencoded. */
    private final Map<String, Encoding> encoding;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("schema", schema.getOpenApiSpec())
                .put("example", example)
                .put("examples", Utils.mapOpenApiSpecAble2Json(examples))
                .put("encoding", Utils.mapOpenApiSpecAble2Json(encoding));
    }
}

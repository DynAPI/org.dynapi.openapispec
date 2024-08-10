package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONObject;

import java.util.List;

@ToString
@Builder(toBuilder = true)
public class ServerVariable implements OpenApiSpecAble {
    /** An enumeration of string values to be used if the substitution options are from a limited set. The array SHOULD NOT be empty. */
    private final List<String> enum_;
    /** The default value to use for substitution, which SHALL be sent if an alternate value is not supplied.
     * Note this behavior is different than the Schema Object's treatment of default values, because in those cases parameter values are optional.
     * If the {@code enum} is defined, the value SHOULD exist in the enum's values. */
    private final String default_;
    /** An optional description for the server variable. CommonMark syntax MAY be used for rich text representation. */
    private final String description;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("enum", enum_)
                .put("default", default_)
                .put("description", description);
    }
}

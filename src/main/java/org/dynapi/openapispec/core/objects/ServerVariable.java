package org.dynapi.openapispec.core.objects;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

@With
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class ServerVariable implements OpenApiSpecAble {
    /** An enumeration of string values to be used if the substitution options are from a limited set. The array SHOULD NOT be empty. */
    public final List<String> enum_;
    /** The default value to use for substitution, which SHALL be sent if an alternate value is not supplied.
     * Note this behavior is different than the Schema Object's treatment of default values, because in those cases parameter values are optional.
     * If the {@code enum} is defined, the value SHOULD exist in the enum's values. */
    public final String default_;
    /** An optional description for the server variable. CommonMark syntax MAY be used for rich text representation. */
    public final String description;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("enum", enum_ == null ? null : new JSONArray(enum_))
                .put("default", default_)
                .put("description", description);
    }
}

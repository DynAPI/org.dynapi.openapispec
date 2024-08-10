package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.With;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

import java.util.List;

@With
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Path implements OpenApiSpecAble {
    /** An optional, string summary, intended to apply to all operations in this path. */
    public final String summary;
    /** An optional, string description, intended to apply to all operations in this path.
     * CommonMark syntax MAY be used for rich text representation. */
    public final String description;
    /** A definition of a GET operation on this path. */
    public final Operation get;
    /** A definition of a GET operation on this path. */
    public final Operation put;
    /** A definition of a POST operation on this path. */
    public final Operation post;
    /** A definition of a DELETE operation on this path. */
    public final Operation delete;
    /** A definition of a OPTIONS operation on this path. */
    public final Operation options;
    /** A definition of a HEAD operation on this path. */
    public final Operation head;
    /** A definition of a PATCH operation on this path. */
    public final Operation patch;
    /** A definition of a TRACE operation on this path. */
    public final Operation trace;
    /** An alternative server array to service all operations in this path. */
    public final List<Server> servers;
    /** A list of parameters that are applicable for all the operations described under this path.
     * These parameters can be overridden at the operation level, but cannot be removed there.
     * The list MUST NOT include duplicated parameters.
     * A unique parameter is defined by a combination of a name and location.
     * The list can use the Reference Object to link to parameters that are defined at the OpenAPI Object's components/parameters. */
    public final List<Parameter> parameters;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("summary", summary)
                .put("description", description)
                .put("get", Utils.getOpenApiSpec(get))
                .put("put", Utils.getOpenApiSpec(put))
                .put("post", Utils.getOpenApiSpec(post))
                .put("delete", Utils.getOpenApiSpec(delete))
                .put("options", Utils.getOpenApiSpec(options))
                .put("head", Utils.getOpenApiSpec(head))
                .put("patch", Utils.getOpenApiSpec(patch))
                .put("trace", Utils.getOpenApiSpec(trace))
                .put("servers", Utils.mapOpenApiSpecAble(servers))
                .put("parameters", Utils.mapOpenApiSpecAble(parameters));
    }
}

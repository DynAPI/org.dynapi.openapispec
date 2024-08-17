package org.dynapi.openapispec.core.objects;

import lombok.*;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.dynapi.openapispec.core.Utils;
import org.json.JSONObject;

import java.util.Map;

@With
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Link implements OpenApiSpecAble {
    /** A relative or absolute URI reference to an OAS operation.
     * This field is mutually exclusive of the {@link Link#operationId} field, and MUST point to an {@link Operation} Object.
     * Relative {@link Link#operationRef} values MAY be used to locate an existing {@link Operation} Object in the OpenAPI definition. */
    public final String operationRef;
    /** The name of an existing, resolvable OAS operation, as defined with a unique {@link Link#operationId}.
     * This field is mutually exclusive of the {@link Link#operationRef} field. */
    public final String operationId;
    /** A map representing parameters to pass to an operation as specified with {@link Link#operationId} or identified via {@link Link#operationRef}.
     * The key is the parameter name to be used, whereas the value can be a constant or an expression to be evaluated and passed to the linked operation.
     * The parameter name can be qualified using the parameter location {@code [{in}.]{name}} for operations that use the same parameter name in different locations (e.g. path.id). */
    public final Map<String, Object> parameters;
    /** A literal value or {@code {expression}} to use as a request body when calling the target operation. */
    public final Object requestBody;
    /** A description of the link. CommonMark syntax MAY be used for rich text representation. */
    public final String description;
    /** A server object to be used by the target operation. */
    public final Server server;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("operationRef", operationRef)
                .put("operationId", operationId)
                .put("parameters", parameters)
                .put("requestBody", requestBody)
                .put("description", description)
                .put("server", Utils.getOpenApiSpec(server));
    }

    /**
     * @see org.dynapi.openapispec.OpenApiSpecBuilder#registerRefParameter(String, Parameter)
     * @see org.dynapi.openapispec.core.objects.Parameter
     */
    @ToString
    @EqualsAndHashCode(callSuper = true)
    public static class Ref extends Link implements OpenApiObjectRef {
        @NonNull
        private final String ref;

        public Ref(@NonNull String name) {
            // just ignore these fields
            super(null, null, null, null, null, null);
            this.ref = name;
        }

        @Override
        public JSONObject getOpenApiSpec() {
            return new JSONObject()
                    .put("$ref", "#/components/links/" + ref);
        }
    }
}

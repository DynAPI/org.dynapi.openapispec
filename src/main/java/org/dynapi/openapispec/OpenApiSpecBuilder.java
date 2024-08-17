package org.dynapi.openapispec;

import lombok.NonNull;
import org.dynapi.openapispec.core.*;
import org.dynapi.openapispec.core.objects.*;
import org.dynapi.openapispec.core.types.Schema;
import org.json.JSONObject;

import java.util.*;
import java.util.List;

public class OpenApiSpecBuilder {
    @NonNull
    protected final Info info;
    protected String logo = null;
    protected final List<Tag> tags = new ArrayList<>();
    protected final Map<String, PathItem> paths = new HashMap<>();
    protected final List<Server> servers = new ArrayList<>();
    protected final Components components = new Components();

    public OpenApiSpecBuilder(Info info) {
        this.info = info;
    }

    /**
     * generates the openapi-specification
     * @return openapi-specification
     */
    public JSONObject build() {
        JSONObject spec = OpenApi.builder()
                .info(info)
                .servers(servers)
                .paths(paths)
                .components(components)
                .tags(tags)
                .build().getOpenApiSpec();

        if (logo != null)
            spec.getJSONObject("info").put("x-logo", new JSONObject()
                    .put("url", logo)
            );

        return spec;
    }

    @Override
    public String toString() {
        return build().toString();
    }

    public String toString(int indentFactor) {
        return build().toString(indentFactor);
    }

    /**
     * adds the x-logo attribute
     * @param logo path to the logo
     */
    public OpenApiSpecBuilder logo(String logo) {
        this.logo = logo;
        return this;
    }

    /**
     * adds {@code path} to the list of endpoints
     * @param path path to add
     * @see PathItem
     * @see PathBuilder
     */
    public OpenApiSpecBuilder addPath(@NonNull String location, @NonNull PathItem path) {
        paths.put(location, path);
        return this;
    }

    /**
     * adds a description to a tag that is referenced in some paths
     * @param tag tag name
     * @see Tag
     */
    public OpenApiSpecBuilder addTag(@NonNull Tag tag) {
        tags.add(tag);
        return this;
    }

    /**
     * @param server server to add
     * @see Server
     * @see ServerBuilder
     */
    public OpenApiSpecBuilder addServer(@NonNull Server server) {
        servers.add(server);
        return this;
    }

    /**
     * @see Schema
     * @see org.dynapi.openapispec.core.types.TRef
     */
    public OpenApiSpecBuilder registerRefSchema(@NonNull String id, Schema<?, ?> schema) {
        return registerRef(ComponentType.SCHEMA, id, schema);
    }

    /**
     * @see Response
     * @see Response.Ref
     */
    public OpenApiSpecBuilder registerRefResponse(@NonNull String id, Response response) {
        return registerRef(ComponentType.RESPONSE, id, response);
    }

    /**
     * @see Parameter
     * @see Parameter.Ref
     */
    public OpenApiSpecBuilder registerRefParameter(@NonNull String id, Parameter parameter) {
        return registerRef(ComponentType.PARAMETER, id, parameter);
    }

    /**
     * @see Example
     * @see Example.Ref
     */
    public OpenApiSpecBuilder registerRefExample(@NonNull String id, Example example) {
        return registerRef(ComponentType.EXAMPLE, id, example);
    }

    /**
     * @see RequestBody
     * @see RequestBody.Ref
     */
    public OpenApiSpecBuilder registerRefRequestBody(@NonNull String id, RequestBody requestBody) {
        return registerRef(ComponentType.REQUEST_BODY, id, requestBody);
    }

    /**
     * @see Header
     * @see Header.Ref
     */
    public OpenApiSpecBuilder registerRefHeader(@NonNull String id, Header header) {
        return registerRef(ComponentType.HEADER, id, header);
    }

    /**
     * @see SecurityScheme
     * @see SecurityScheme.Ref
     */
    public OpenApiSpecBuilder registerRefSecurityScheme(@NonNull String id, SecurityScheme header) {
        return registerRef(ComponentType.SECURITY_SCHEME, id, header);
    }

    /**
     * @see Link
     * @see Link.Ref
     */
    public OpenApiSpecBuilder registerRefLink(@NonNull String id, Link link) {
        return registerRef(ComponentType.LINK, id, link);
    }

    /**
     * @see PathItem
     * @see PathItem.Ref
     */
    public OpenApiSpecBuilder registerRefPath(@NonNull String id, PathItem pathItem) {
        return registerRef(ComponentType.PATH, id, pathItem);
    }

    /**
     * @see OpenApiObjectRef
     * @see ComponentType
     */
    public OpenApiSpecBuilder registerRef(@NonNull ComponentType type, @NonNull String id, OpenApiSpecAble value) {
        switch (type) {
            case SCHEMA -> components.schemas.put(id, (Schema<?, ?>) value);
            case PARAMETER -> components.parameters.put(id, (Parameter) value);
            case EXAMPLE -> components.examples.put(id, (Example) value);
            case HEADER -> components.headers.put(id, (Header) value);
            case RESPONSE -> components.responses.put(id, (Response) value);
            case REQUEST_BODY -> components.requestBodies.put(id, (RequestBody) value);
            case SECURITY_SCHEME -> components.securitySchemes.put(id, (SecurityScheme) value);
            case LINK -> components.links.put(id, (Link) value);
            case PATH -> components.pathItems.put(id, (PathItem) value);
            default -> throw new IllegalStateException("Unsupported value: " + type);
        }
        return this;
    }
}

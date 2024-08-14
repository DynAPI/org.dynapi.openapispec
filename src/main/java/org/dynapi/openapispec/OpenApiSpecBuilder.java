package org.dynapi.openapispec;

import lombok.NonNull;
import org.dynapi.openapispec.core.*;
import org.dynapi.openapispec.core.objects.*;
import org.dynapi.openapispec.core.types.Schema;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class OpenApiSpecBuilder {
    protected final Info info;
    protected String logo = null;
    protected final List<Tag> tags = new ArrayList<>();
    protected final Map<String, Path> paths = new HashMap<>();
    protected final List<Server> servers = new ArrayList<>();
    protected final JSONObject components = new JSONObject();

    public OpenApiSpecBuilder(Info info) {
        this.info = info;
    }

    protected String getFormattedDescription() {
        return getFormattedDescription(null);
    }

    protected String getFormattedDescription(Throwable error) {
        StringBuilder description = new StringBuilder();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String timestamp = dateFormat.format(new Date());
        description.append(String.format("Last-Updated: %s\n\n", timestamp));

        if (error != null) {
            description.append("# Failed to generate openapi-specification\n");
            description.append(String.format("**Type:** %s\n", error.getClass().getSimpleName()));
            if (error.getMessage() != null)
                description.append(String.format("**Detail:** %s\n\n", error.getMessage()));
            if (info.description != null)
                description.append("# Description\n");
        }

        if (info.description != null)
            description.append(String.format("%s\n", info.description));

        return description.toString();
    }

    /**
     * generates the openapi-specification
     * @return openapi-specification
     */
    public JSONObject build() {
        JSONObject spec = new JSONObject()
                .put("openapi", "3.0.0")
                .put("info", Info.builder()
                        .title(info.title)
                        .version(info.version)
                        .description(getFormattedDescription())
                        .license(info.license)
                        .contact(info.contact)
                        .termsOfServiceUrl(info.termsOfServiceUrl)
                        .build().getOpenApiSpec()
                );
        if (logo != null)
            spec.getJSONObject("info").put("x-logo", new JSONObject()
                    .put("url", logo)
            );

        if (!servers.isEmpty())
            spec.put("servers", Utils.mapOpenApiSpecAble(servers));

        JSONObject paths = new JSONObject();
        for (var entry : this.paths.entrySet()) {
            paths.put(entry.getKey(), entry.getValue().getOpenApiSpec());
        }
        spec.put("paths", paths);

        if (!tags.isEmpty())
            spec.put("tags", Utils.mapOpenApiSpecAble(tags));

        if (!components.isEmpty())
            spec.put("components", components);

        return spec;
    }

    /**
     * generates the openapi-specification with error-details in the description
     * @param error error during previous generation
     * @return openapi-specification
     */
    public JSONObject build(Throwable error) {
        JSONObject spec = new JSONObject()
                .put("openapi", "3.0.0")
                .put("info", Info.builder()
                        .title(info.title)
                        .version(info.version)
                        .description(getFormattedDescription(error))
                        .build().getOpenApiSpec()
                );
        if (logo != null)
            spec.put("x-logo", new JSONObject()
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
     * @see Path
     * @see PathBuilder
     */
    public OpenApiSpecBuilder addPath(@NonNull String location, @NonNull Path path) {
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
     * @see OpenApiObjectRef
     * @see ComponentType
     */
    public OpenApiSpecBuilder registerRef(@NonNull ComponentType type, @NonNull String id, OpenApiSpecAble value) {
        return registerRef(type, id, value.getOpenApiSpec());
    }

    public OpenApiSpecBuilder registerRef(@NonNull ComponentType type, @NonNull String id, JSONObject value) {
        if (!components.has(type.value))
            components.put(type.value, new JSONObject());
        components.getJSONObject(type.value).put(id, value);
        return this;
    }
}

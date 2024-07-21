package org.dynapi.openapispec;

import lombok.Builder;
import lombok.NonNull;
import org.dynapi.openapispec.core.Path;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class OpenApiSpecBuilder {
    protected final Meta meta;
    protected final Map<String, String> tagInfos = new HashMap<>();
    protected final List<Path> paths = new ArrayList<>();
    protected final List<JSONObject> servers = new ArrayList<>();

    public OpenApiSpecBuilder(Meta meta) {
        this.meta = meta;
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
            if (meta.description != null)
                description.append("# Description\n");
        }

        if (meta.description != null)
            description.append(String.format("%s\n", meta.description));

        return description.toString();
    }

    /**
     * generates the openapi-specification
     * @return openapi-specification
     */
    public JSONObject build() {
        JSONObject spec = new JSONObject()
                .put("openapi", "3.0.0")
                .put("info", new JSONObject()
                        .put("title", meta.title)
                        .put("version", meta.version)
                        .put("description", getFormattedDescription())
                );
        if (meta.logo != null)
            spec.put("x-logo", new JSONObject()
                    .put("url", meta.logo)
            );

        if (!servers.isEmpty())
            spec.put("servers", servers);

        JSONObject paths = new JSONObject();
        for (Path path : this.paths) {
            paths.put(path.getPath(), path.getOpenApiSpec());
        }
        spec.put("paths", paths);

        JSONArray tags = new JSONArray();
        for (Map.Entry<String, String> entry : tagInfos.entrySet()) {
            tags.put(new JSONObject()
                    .put("name", entry.getKey())
                    .put("description", entry.getValue())
            );
        }
        spec.put("tags", tags);

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
                .put("info", new JSONObject()
                        .put("title", meta.title)
                        .put("version", meta.version)
                        .put("description", getFormattedDescription(error))
                );
        if (meta.logo != null)
            spec.put("x-logo", new JSONObject()
                    .put("url", meta.logo)
            );
        return spec;
    }

    @Override
    public String toString() {
        return build().toString();
    }

    /**
     * adds {@code path} to the list of endpoints
     * @param path path to add
     */
    public OpenApiSpecBuilder addPath(@NonNull Path path) {
        paths.add(path);
        return this;
    }

    /**
     * adds a description to a tag that is referenced in some paths
     * @param tag tag name
     * @param description tag description
     */
    public OpenApiSpecBuilder addTag(@NonNull String tag, @NonNull String description) {
        tagInfos.put(tag, description);
        return this;
    }

    /**
     * @param url the url of the server
     */
    public OpenApiSpecBuilder addServer(@NonNull String url) {
        return addServer(url, null);
    }

    /**
     * @param url the url of the server
     * @param description description of this server
     */
    public OpenApiSpecBuilder addServer(@NonNull String url, String description) {
        JSONObject server = new JSONObject()
                .put("url", url);
        if (description != null)
            server.put("description", description);
        servers.add(server);
        return this;
    }

    @Builder(toBuilder = true)
    public static class Meta {
        public final String title;
        public final String version;
        public final String description;
        public final String logo;
    }
}

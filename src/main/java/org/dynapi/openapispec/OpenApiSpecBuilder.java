package org.dynapi.openapispec;

import lombok.Builder;
import org.dynapi.openapispec.core.Path;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class OpenApiSpecBuilder {
    private final Meta meta;
    private final Map<String, String> tagInfos = new HashMap<>();
    private final List<Path> paths = new ArrayList<>();

    public OpenApiSpecBuilder(Meta meta) {
        this.meta = meta;
    }

    protected String getFormattedDescription() {
        return getFormattedDescription(null);
    }

    protected String getFormattedDescription(Throwable error) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String timestamp = dateFormat.format(new Date());
        String errorInfo = "";
        if (error != null) {
            errorInfo = new StringBuilder()
                    .append("# Failed to generate openapi-specification\n")
                    .append(String.format("**Type:** %s\n", error.getClass().getSimpleName()))
                    .append(String.format("**Detail:** %s\n", error.getMessage()))
                    .toString();
        }
        String description = meta.description;
        return String.format("Last-Updated: %s\n%s\n%s", timestamp, errorInfo, description);
    }

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

    public void addPath(Path path) {
        paths.add(path);
    }

    public void addTag(String tag, String description) {
        tagInfos.put(tag, description);
    }

    @Builder(toBuilder = true)
    public static class Meta {
        public final String title;
        public final String version;
        public final String description;
        public final String logo;
    }
}

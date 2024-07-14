package org.dynapi.openapispec;

import org.dynapi.openapispec.core.Path;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class OpenApiSpec {
    private final OpenApiInfo info;
    private final Map<String, String> tagInfos = new HashMap<>();
    private final List<Path> paths = new ArrayList<>();

    public OpenApiSpec(OpenApiInfo info) {
        this.info = info;
    }

    private String getFormattedDescription(Throwable error) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String timestamp = dateFormat.format(new Date());
        String errorInfo = "";
        if (error != null) {
            errorInfo = String.format("""
                    # Failed to generate openapi-specification
                    **Type:** %s
                    **Detail:** %s
                    """, error.getClass().getSimpleName(), error.getMessage());
        }
        String description = info.description;
        return String.format("Last-Updated: %s\n%s\n%s", timestamp, errorInfo, description);
    }

    public JSONObject build() {
        JSONObject spec = new JSONObject()
                .put("openapi", "3.0.0")
                .put("info", new JSONObject()
                        .put("title", info.title)
                        .put("version", info.version)
                        .put("description", info.description)
                );
        if (info.logo != null)
            spec.put("x-logo", new JSONObject()
                    .put("url", info.logo)
            );
        try {
            JSONObject paths = new JSONObject();
            for (Path path : this.paths) {
                paths.put(path.getPath(), path.getOpenApiSpec());
            }
            JSONArray tags = new JSONArray();
            for (Map.Entry<String, String> entry : tagInfos.entrySet()) {
                tags.put(new JSONObject()
                        .put("name", entry.getKey())
                        .put("description", entry.getValue())
                );
            }
            spec.put("tags", tags);
            spec.put("paths", paths);
        } catch (Exception error) {
            spec.getJSONObject("info").put("description", getFormattedDescription(error));
        }
        return spec;
    }

    public void addPath(Path path) {
        paths.add(path);
    }

    public void addTag(String tag, String description) {
        tagInfos.put(tag, description);
    }
}

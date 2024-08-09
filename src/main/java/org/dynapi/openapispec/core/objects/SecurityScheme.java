package org.dynapi.openapispec.core.objects;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.dynapi.openapispec.core.OpenApiSpecAble;
import org.json.JSONObject;

@ToString
@Builder(toBuilder = true)
public class SecurityScheme implements OpenApiSpecAble {
    @NonNull
    public final Type type;
    public final String description;
    public final String apiKeyHeaderName;
    public final ApiKeyIn apiKeyIn;
    public final String httpScheme;
    public final String httpBearerFormat;
//    public final OAuthFlows flows;
    public final String openIdConnectUrl;

    @Override
    public JSONObject getOpenApiSpec() {
        return new JSONObject()
                .put("type", type.value)
                .put("description", description)
                .put("name", apiKeyHeaderName)
                .put("in", apiKeyIn == null ? null : apiKeyIn.value)
                .put("scheme", httpScheme)
                .put("bearerFormat", httpBearerFormat)
//                .put("flows", flows)
                .put("openIdConnectUrl", openIdConnectUrl);
    }

    public enum Type {
        API_KEY("apiKey"),
        HTTP("http"),
        OAUTH2("oauth2"),
        OPEN_ID_CONNECT("openIdConnect"),
        ;

        public final String value;

        Type(String value) {
            this.value = value;
        }
    }

    public enum ApiKeyIn {
        QUERY("query"),
        HEADER("header"),
        COOKIE("cookie"),
        ;

        public final String value;

        ApiKeyIn(String value) {
            this.value = value;
        }
    }
}

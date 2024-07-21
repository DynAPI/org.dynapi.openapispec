package org.dynapi.openapispec.core.types;

import lombok.NonNull;
import lombok.ToString;
import org.json.JSONObject;

import java.util.regex.Pattern;

@ToString(callSuper = true)
public class TString extends Schema<TString> {
    public TString() {
        super();
    }

    public TString minLength(int minProperties) {
        options.put("minProperties", minProperties);
        return this;
    }

    public TString maxLength(int maxProperties) {
        options.put("maxProperties", maxProperties);
        return this;
    }

    public TString format(@NonNull String fmt) {
        options.put("format", fmt);
        return this;
    }

    public TString pattern(@NonNull String pattern) {
        options.put("pattern", pattern);
        return this;
    }

    public TString pattern(@NonNull Pattern pattern) {
        options.put("pattern", pattern.pattern());
        return this;
    }

    @Override
    protected JSONObject finalized() {
        return new JSONObject()
                .put("type", "string");
    }

    public static class CommonFormats {
        // OpenAPI built-in string formats
        public static final String BYTE = "byte";
        public static final String BINARY = "binary";
        public static final String DATE = "date";
        public static final String DATETIME = "date-time";
        public static final String PASSWORD = "password";
        // free-form string formats
        public static final String EMAIL = "email";
        public static final String UUID = "uuid";
        public static final String URI = "uri";
        public static final String HOSTNAME = "hostname";
        public static final String IPV4 = "ipv4";
        public static final String IPV6 = "ipv6";
    }
}

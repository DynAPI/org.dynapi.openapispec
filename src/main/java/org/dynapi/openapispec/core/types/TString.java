package org.dynapi.openapispec.core.types;

import lombok.*;
import org.json.JSONObject;

import java.util.regex.Pattern;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TString extends Schema<TString, String> {
    public TString() {
        super();
    }

    /**
     *
     * @param minLength minimum length in characters
     */
    public TString minLength(int minLength) {
        options.put("minLength", minLength);
        return this;
    }

    /**
     * @param maxLength maximum length in characters
     */
    public TString maxLength(int maxLength) {
        options.put("maxLength", maxLength);
        return this;
    }

    /**
     * note: see {@code TString.CommonFormats.*}
     * @param fmt name of a format
     */
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

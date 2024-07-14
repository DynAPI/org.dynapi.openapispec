package org.dynapi.openapispec.core.types;

import lombok.ToString;
import org.json.JSONObject;

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

    public TString format(String fmt) {
        options.put("format", fmt);
        return this;
    }

    public TString pattern(String pattern) {
        options.put("pattern", pattern);
        return this;
    }

    @Override
    protected JSONObject finalized() {
        return new JSONObject()
                .put("type", "string");
    }

    public static class CommonFormats {
        public static final String BYTE = "byte";
        public static final String DATE = "date";
        public static final String IPV4 = "ipv4";
        public static final String IPV6 = "ipv6";
        public static final String XML = "xml";
    }
}

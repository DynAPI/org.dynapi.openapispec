package org.dynapi.openapispec.core.schema;

import lombok.*;
import org.json.JSONObject;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TNumber extends Schema<TNumber, Double> {
    public TNumber gte(double value) {
        options.put("minimum", value);
        options.put("exclusiveMinimum", false);
        return this;
    }

    public TNumber gt(double value) {
        options.put("minimum", value);
        options.put("exclusiveMinimum", true);
        return this;
    }

    public TNumber lte(double value) {
        options.put("maximum", value);
        options.put("exclusiveMaximum", false);
        return this;
    }

    public TNumber lt(double value) {
        options.put("maximum", value);
        options.put("exclusiveMaximum", true);
        return this;
    }

    public TNumber multipleOf(double value) {
        options.put("multipleOf", value);
        return this;
    }

    /**
     * @param format name of a format
     * @see CommonFormats
     */
    public TNumber format(@NonNull String format) {
       options.put("format", format);
       return this;
    }

    @Override
    protected JSONObject finalized() {
        return new JSONObject()
                .put("type", "number");
    }

    @Override
    public TNumber copy() {
        TNumber copy = new TNumber();
        copy.options.putAll(this.options);
        return copy;
    }

    public static class CommonFormats {
        public static final String INT32 = "int32";
        public static final String INT64 = "int64";
        public static final String LONG = INT64;
    }
}

package org.dynapi.openapispec.core.types;

import lombok.*;
import org.json.JSONObject;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TInteger extends Schema<TInteger, Long> {
    public TInteger gte(long value) {
        options.put("minimum", value);
        options.put("exclusiveMinimum", false);
        return this;
    }

    public TInteger gt(long value) {
        options.put("minimum", value);
        options.put("exclusiveMinimum", true);
        return this;
    }

    public TInteger lte(long value) {
        options.put("maximum", value);
        options.put("exclusiveMaximum", false);
        return this;
    }

    public TInteger lt(long value) {
        options.put("maximum", value);
        options.put("exclusiveMaxmimum", true);
        return this;
    }

    public TInteger multipleOf(long value) {
        options.put("multipleOf", value);
        return this;
    }

    @Override
    protected JSONObject finalized() {
        return new JSONObject()
                .put("type", "integer");
    }
}

package org.dynapi.openapispec.core.types;

import lombok.ToString;
import org.json.JSONObject;

@ToString(callSuper = true)
public class TNumber extends Schema<TNumber, Double> {
    public TNumber() {
        super();
    }

    public TNumber gte(int value) {
        options.put("minimum", value);
        options.put("exclusiveMinimum", false);
        return this;
    }

    public TNumber gt(int value) {
        options.put("minimum", value);
        options.put("exclusiveMinimum", true);
        return this;
    }

    public TNumber lte(int value) {
        options.put("maximum", value);
        options.put("exclusiveMaximum", false);
        return this;
    }

    public TNumber lt(int value) {
        options.put("maximum", value);
        options.put("exclusiveMaxmimum", true);
        return this;
    }

    public TNumber multipleOf(double value) {
        options.put("multipleOf", value);
        return this;
    }

    @Override
    protected JSONObject finalized() {
        return new JSONObject()
                .put("type", "number");
    }
}

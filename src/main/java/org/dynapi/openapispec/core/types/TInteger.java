package org.dynapi.openapispec.core.types;

import lombok.ToString;
import org.json.JSONObject;

@ToString(callSuper = true)
public class TInteger extends Schema<TInteger> {
    public TInteger() {
        super();
    }

    public TInteger gte(int value) {
        options.put("minimum", value);
        options.put("exclusiveMinimum", false);
        return this;
    }

    public TInteger gt(int value) {
        options.put("minimum", value);
        options.put("exclusiveMinimum", true);
        return this;
    }

    public TInteger lte(int value) {
        options.put("maximum", value);
        options.put("exclusiveMaximum", false);
        return this;
    }

    public TInteger lt(int value) {
        options.put("maximum", value);
        options.put("exclusiveMaxmimum", true);
        return this;
    }

    public TInteger multipleOf(int value) {
        options.put("multipleOf", value);
        return this;
    }

    public TInteger multipleOf(double value) {
        options.put("multipleOf", value);
        return this;
    }

    @Override
    protected JSONObject finalized() {
        return new JSONObject()
                .put("type", "integer");
    }
}

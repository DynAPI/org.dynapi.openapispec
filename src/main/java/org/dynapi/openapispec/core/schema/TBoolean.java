package org.dynapi.openapispec.core.schema;

import lombok.*;
import org.json.JSONObject;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TBoolean extends Schema<TBoolean, Boolean> {
    @Override
    protected JSONObject finalized() {
        return new JSONObject()
                .put("type", "boolean");
    }

    @Override
    public TBoolean copy() {
        TBoolean copy = new TBoolean();
        copy.options.putAll(this.options);
        return copy;
    }
}

package org.dynapi.openapispec.core.types;

import lombok.ToString;
import org.json.JSONObject;

@ToString(callSuper = true)
public class TBoolean extends Schema<TBoolean, Boolean> {
    @Override
    protected JSONObject finalized() {
        return new JSONObject()
                .put("type", "boolean");
    }
}

package org.dynapi.openapispec.core.types;

import lombok.ToString;
import org.json.JSONObject;

@ToString(callSuper = true)
public class TAnyType extends Schema<TAnyType, Object> {
    @Override
    protected JSONObject finalized() {
        return new JSONObject()
                .put("AnyValue", new JSONObject());
    }
}

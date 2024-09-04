package org.dynapi.openapispec.core.schema;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.dynapi.openapispec.core.ComponentType;
import org.json.JSONObject;

@ToString
@EqualsAndHashCode(callSuper = true)
public class TRef extends Schema<TRef, Void> {
    protected final ComponentType type;
    protected final String name;

    public TRef(ComponentType type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    protected JSONObject finalized() {
        return new JSONObject()
                .put("$ref", String.format("#/components/%s/%s", type.value, name));
    }

    @Override
    public TRef copy() {
        TRef copy = new TRef(type, name);
        copy.options.putAll(this.options);
        return copy;
    }
}

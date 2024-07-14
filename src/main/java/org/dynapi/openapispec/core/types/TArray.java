package org.dynapi.openapispec.core.types;

import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ToString(callSuper = true)
public class TArray extends Schema<TArray> {
    private final List<Schema<?>> types = new ArrayList<>();

    public TArray(Schema<?>... types) {
        super();
        this.types.addAll(Arrays.asList(types));
    }

    public TArray size(int size) {
        options.put("minItems", size);
        options.put("maxItems", size);
        return this;
    }

    public TArray minSize(int minSize) {
        options.put("minItems", minSize);
        return this;
    }

    public TArray maxSize(int maxSize) {
        options.put("maxItems", maxSize);
        return this;
    }

    public TArray uniqueItems() { return uniqueItems(true); }
    public TArray uniqueItems(boolean uniqueItems) {
        options.put("uniqueItems", uniqueItems);
        return this;
    }

    public TArray addType(Schema<?> schema) {
        types.add(schema);
        return this;
    }

    @Override
    protected JSONObject finalized() {
        JSONObject finalizedItems;
        if (types.isEmpty()) {
            finalizedItems = new JSONObject();
        } else if (types.size() == 1) {
            finalizedItems = types.get(0).finalized();
        } else {
            JSONArray array = new JSONArray();
            for (Schema<?> item : types) {
                array.put(item.finalized());
            }
            finalizedItems = new JSONObject()
                    .put("anyOf", array);
        }

        return new JSONObject()
                .put("type", "array")
                .put("items", finalizedItems);
    }
}

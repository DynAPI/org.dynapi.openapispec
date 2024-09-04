package org.dynapi.openapispec.core.schema;

import lombok.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TArray extends Schema<TArray, Object[]> {
    private final List<Schema<?, ?>> types = new ArrayList<>();

    public TArray(Schema<?, ?>... types) {
        this.types.addAll(Arrays.asList(types));
    }

    public TArray(Collection<Schema<?, ?>> types) {
        this.types.addAll(types);
    }

    /**
     * @param size exact number of items in this array
     */
    public TArray size(int size) {
        options.put("minItems", size);
        options.put("maxItems", size);
        return this;
    }

    /**
     * @param minSize minimum number of items in this array
     */
    public TArray minSize(int minSize) {
        options.put("minItems", minSize);
        return this;
    }

    /**
     * @param maxSize maximum number of items in this array
     */
    public TArray maxSize(int maxSize) {
        options.put("maxItems", maxSize);
        return this;
    }

    /**
     * marks that all items should be unique
     */
    public TArray uniqueItems() { return uniqueItems(true); }
    /**
     * marks that all items should be unique
     */
    public TArray uniqueItems(boolean uniqueItems) {
        options.put("uniqueItems", uniqueItems);
        return this;
    }

    /**
     * note: use this multiple times to allow multiple schemas
     * @param schema schema for the values
     */
    public TArray addType(@NonNull Schema<?, ?> schema) {
        types.add(schema);
        return this;
    }

    @Override
    protected JSONObject finalized() {
        JSONObject items;
        if (types.isEmpty()) {
            items = new JSONObject();
        } else if (types.size() == 1) {
            items = types.get(0).getOpenApiSpec();
        } else {
            items = new JSONObject()
                    .put("oneOf", new JSONArray(types.stream().map(Schema::getOpenApiSpec).toList()));
        }

        return new JSONObject()
                .put("type", "array")
                .put("items", items);
    }

    @Override
    public TArray copy() {
        TArray copy = new TArray();
        copy.options.putAll(this.options);
        copy.types.addAll(this.types);
        return copy;
    }
}

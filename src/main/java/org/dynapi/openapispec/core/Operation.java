package org.dynapi.openapispec.core;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Operation {
    public static final Operation GET = new Operation("get");
    public static final Operation POST = new Operation("post");
    public static final Operation PUT = new Operation("put");
    public static final Operation PATCH = new Operation("patch");
    public static final Operation DELETE = new Operation("delete");
    public static final Operation HEAD = new Operation("head");
    public static final Operation OPTIONS = new Operation("options");
    public static final Operation TRACE = new Operation("trace");

    public final String value;

    @Override
    public String toString() {
        return value;
    }
}

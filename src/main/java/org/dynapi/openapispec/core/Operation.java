package org.dynapi.openapispec.core;

public enum Operation {
    GET("get"),
    POST("post"),
    PUT("put"),
    PATCH("patch"),
    DELETE("delete"),
    HEAD("head"),
    OPTIONS("options"),
    TRACE("trace"),
    ;

    public final String value;

    Operation(String value) {
        this.value = value;
    }
}

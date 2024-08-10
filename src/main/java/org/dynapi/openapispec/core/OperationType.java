package org.dynapi.openapispec.core;

public enum OperationType {
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

    OperationType(String value) {
        this.value = value;
    }
}

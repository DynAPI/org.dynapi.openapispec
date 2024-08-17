package org.dynapi.openapispec.core;

public enum ComponentType {
    SCHEMA("schemas"),
    PARAMETER("parameters"),
    SECURITY_SCHEME("securitySchemes"),
    REQUEST_BODY("requestBodies"),
    RESPONSE("responses"),
    HEADER("headers"),
    EXAMPLE("examples"),
    LINK("links"),
    CALLBACK("callbacks"),
    PATH("pathItems"),
    ;

    public final String value;

    ComponentType(String value) {
        this.value = value;
    }
}

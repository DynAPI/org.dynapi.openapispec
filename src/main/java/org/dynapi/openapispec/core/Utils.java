package org.dynapi.openapispec.core;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    private final static Map<Integer, String> code2text = new HashMap<>();

    static {
        code2text.put(1, "Informational");
        code2text.put(2, "Successful Operation");
        code2text.put(3, "Redirect");
        code2text.put(4, "Bad Request");
        code2text.put(5, "Server Error");
    }

    /**
     * @param status http status-code
     * @return http response class
     */
    public static String statusCodeToText(int status) {
        return code2text.getOrDefault(status / 100, "Unknown Operation");
    }
}

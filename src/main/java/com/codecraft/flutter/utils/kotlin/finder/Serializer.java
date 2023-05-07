package com.codecraft.flutter.utils.kotlin.finder;

import org.json.simple.JSONObject;

import java.util.Base64;
import java.util.Map;

public class Serializer {

    public static String serialize(Map<String, Object> o) {
        String jsonStringified = new JSONObject(o).toJSONString();
        String base64Encoded = Base64.getEncoder().encodeToString(jsonStringified.getBytes());
        return base64Encoded;
    }

}

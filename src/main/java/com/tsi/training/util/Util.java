package com.tsi.training.util;

import java.util.LinkedHashMap;

public class Util {

    static String getStringOrNull(LinkedHashMap<String, Object> map, String key) {
        return map.containsKey(key) ? (String) map.get(key) : null;
    }

    static Double getDoubleOrNull(LinkedHashMap<String, Object> map, String key) {
        return map.containsKey(key) ? (Double) map.get(key) : null;
    }

}

package com.tsi.training;

import com.dashjoin.jsonata.Utils;
import com.google.gson.Gson;

import java.util.LinkedHashMap;

import static com.dashjoin.jsonata.Jsonata.jsonata;

public class JSONataMapper {
    public static Utils.JList Map(String json, String expression) {
        Gson gson = new Gson();
        var data = gson.fromJson(json, Object.class);

        var expr = jsonata(expression);
        return (Utils.JList) expr.evaluate(data);
    }
}

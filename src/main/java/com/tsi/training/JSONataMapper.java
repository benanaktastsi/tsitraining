package com.tsi.training;

import com.google.gson.Gson;
import com.tsi.training.dto.response.OrderDTO;

import static com.dashjoin.jsonata.Jsonata.jsonata;

public class JSONataMapper {
    public static OrderDTO map(String json, String expression) {
        Gson gson = new Gson();
        var data = gson.fromJson(json, Object.class);

        var expr = jsonata(expression);
        return (OrderDTO) expr.evaluate(data);
    }
}

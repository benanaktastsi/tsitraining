package com.tsi.training.util;

import com.dashjoin.jsonata.Utils.JList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tsi.training.dto.response.OrderDTO;
import com.tsi.training.dto.response.OrderItemDTO;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.dashjoin.jsonata.Jsonata.jsonata;

public class JSONataMapper {

    private static final Path expressionPath = Path.of("./src/main/resources/JSONataExpression.txt");
    private static final String PARTS_KEY = "Parts";

    private static OrderDTO fromLinkedHashMap(LinkedHashMap<String, Object> input){
        OrderDTO output = new OrderDTO();

        output.setDealerDescription(getStringOrNull(input, "DealerDescription"));
        output.setOrderReference(getStringOrNull(input, "OrderReference"));
        output.setOrderDate(getStringOrNull(input, "OrderDate"));
        output.setCustomerName(getStringOrNull(input, "CustomerName"));
        output.setMobile(getStringOrNull(input, "Mobile"));
        output.setPostcode(getStringOrNull(input, "Postcode"));

        List<LinkedHashMap<String, Object>> partsList = getPartsList(input);
        output.setParts(partsList.stream()
                .map(part -> {
                    OrderItemDTO partDTO = new OrderItemDTO();
                    partDTO.setPrice(getDoubleOrNull(part, "Price"));
                    partDTO.setProposedDate(getStringOrNull(part, "ProposedDate"));
                    partDTO.setPartDescription(getStringOrNull(part, "PartDescription"));
                    return partDTO;
                }).collect(Collectors.toList()));

        return output;
    }

    private static String getStringOrNull(LinkedHashMap<String, Object> map, String key) {
        return map.containsKey(key) ? (String) map.get(key) : null;
    }

    private static Double getDoubleOrNull(LinkedHashMap<String, Object> map, String key) {
        return map.containsKey(key) ? (Double) map.get(key) : null;
    }

    @SuppressWarnings("unchecked")
    private static List<LinkedHashMap<String, Object>> getPartsList(LinkedHashMap<String, Object> input) {
        Object parts = input.get(PARTS_KEY);
        if (parts instanceof JList) {
            return ((JList<LinkedHashMap<String, Object>>) parts).stream()
                    //.map(LinkedHashMap.class::cast)
                    .collect(Collectors.toList());
        } else if (parts instanceof LinkedHashMap) {
            return List.of((LinkedHashMap<String, Object>) parts);
        } else {
            return List.of(); // Return an empty list if parts is neither JList nor LinkedHashMap
        }
    }

    public static List<OrderDTO> map(String json) throws IOException {
        Gson gson = new Gson();
        var data = gson.fromJson(json, new TypeToken<LinkedHashMap<String, Object>>(){}.getType());

        String expression = Files.readString(expressionPath, Charset.defaultCharset());
        var expr = jsonata(expression);
        var result = (JList<LinkedHashMap<String, Object>>) expr.evaluate(data);
        return result.stream()
                .map(JSONataMapper::fromLinkedHashMap)
                .collect(Collectors.toList());
    }
}

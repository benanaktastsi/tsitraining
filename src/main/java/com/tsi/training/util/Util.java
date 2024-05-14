package com.tsi.training.util;

import com.tsi.training.dto.PartOrder;
import com.tsi.training.dto.response.OrderDTO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    static String getStringOrNull(LinkedHashMap<String, Object> map, String key) {
        return map.containsKey(key) ? (String) map.get(key) : null;
    }

    static Double getDoubleOrNull(LinkedHashMap<String, Object> map, String key) {
        return map.containsKey(key) ? (Double) map.get(key) : null;
    }

    public static List<PartOrder> fromOrder(OrderDTO order){
        return order.getParts().stream().map(part -> new PartOrder(order, part)).collect(Collectors.toList());
    }

}

package com.tsi.training.util;

import com.google.gson.Gson;
import com.tsi.training.dto.PartOrder;
import com.tsi.training.dto.response.OrderDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tsi.training.util.Util.fromOrder;

public class OrderMapper {

    private static final Gson gson = new Gson();

    public static Map<String, String> splitIntoStrings(ProcessResponse response){
        HashMap<String, String> output = new HashMap<>();
        splitIntoParts(response).forEach((key,value) -> output.put(key, gson.toJson(value)));
        return output;
    }

    private static Map<String, List<PartOrder>> splitIntoParts(ProcessResponse response){
        HashMap<String, List<PartOrder>> dealerParts = new HashMap<>();

        for (OrderDTO order: response.getOrders()){
            dealerParts.merge(order.getDealerDescription(), fromOrder(order), (val, newval)->{
                val.addAll(newval);
                return val;
            });
        }

        return dealerParts;
    }

}

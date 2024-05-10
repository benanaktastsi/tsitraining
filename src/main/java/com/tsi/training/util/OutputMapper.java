package com.tsi.training.util;

import com.google.gson.Gson;
import com.tsi.training.dto.response.OrderDTO;
import com.tsi.training.dto.response.OrderItemDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutputMapper {

    private static final Gson gson = new Gson();

    public static Map<String, String> splitIntoStrings(ProcessResponse response){
        HashMap<String, String> output = new HashMap<>();
        splitIntoParts(response).forEach((key,value) -> output.put(key, gson.toJson(value)));
        return output;
    }


    private static Map<String, List<PartOrder>> splitIntoParts(ProcessResponse response){
        HashMap<String, List<PartOrder>> dealerParts = new HashMap<>();

        for (OrderDTO order: response.getOrders()){
            dealerParts.merge(order.getDealerDescription(), PartOrder.fromOrder(order), (val, newval)->{
                val.addAll(newval);
                return val;
            });
        }

        return dealerParts;
    }
}


class PartOrder{
    private double price;
    private String proposedDate;
    private String partDescription;
    private String dealerDescription;
    private String orderReference;
    private String orderDate;
    private String customerName;
    private String mobile;
    private String postcode;

    private PartOrder(OrderDTO order, OrderItemDTO part){
        this.price = part.getPrice();
        this.proposedDate = part.getProposedDate();
        this.partDescription = part.getPartDescription();
        this.dealerDescription = order.getDealerDescription();
        this.orderReference = order.getOrderReference();
        this.orderDate = order.getOrderDate();
        this.customerName = order.getCustomerName();
        this.mobile = order.getCustomerName();
        this.postcode = order.getPostcode();
    }

    public static List<PartOrder> fromOrder(OrderDTO order){
        return order.getParts().stream().map(part -> {
                return new PartOrder(order, part);
        }).collect(Collectors.toList());
    }
}

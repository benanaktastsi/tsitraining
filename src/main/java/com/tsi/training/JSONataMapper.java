package com.tsi.training;

import com.google.gson.Gson;
import com.tsi.training.dto.response.OrderDTO;
import com.dashjoin.jsonata.Utils.JList;
import com.tsi.training.dto.response.OrderItemDTO;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.dashjoin.jsonata.Jsonata.jsonata;

public class JSONataMapper {

    private static final Path expressionPath = Path.of("./src/main/resources/JSONataExpression.txt");
    //private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static OrderDTO fromLinkedHashMap(LinkedHashMap<String, Object> input){
        OrderDTO output = new OrderDTO();

        output.setDealerDescription((String) input.get("DealerDescription"));
        output.setOrderReference((String) input.get("OrderReference"));
        output.setOrderDate((String) input.get("OrderDate"));
        output.setCustomerName((String) input.get("CustomerName"));
        output.setMobile((String) input.get("Mobile"));
        output.setPostcode((String) input.get("Postcode"));

        Stream<LinkedHashMap<String, Object>> partsStream;
        //if the parts list contains 1 element, it appears as a LinkedHashMap
        //if there are multiple elements, it is a JList of LinkedHashMaps
        //in either case turn into a stream
        if (input.get("Parts") instanceof JList){
            partsStream = ((JList<LinkedHashMap<String, Object>>) input.get("Parts")).stream();
        } else {
            partsStream = Stream.of((LinkedHashMap<String, Object>) input.get("Parts"));
        }

        output.setParts(partsStream
                .map((LinkedHashMap<String, Object> part) -> {
                    OrderItemDTO partDTO = new OrderItemDTO();
                    partDTO.setPrice((double) part.get("Price"));
                    partDTO.setProposedDate((String) part.get("ProposedDate"));
                    partDTO.setPartDescription((String) part.get("PartDescription"));
                    return partDTO;
                }).collect(Collectors.toList()));

        return output;
    }

    public static List<OrderDTO> map(String json) throws IOException {
        Gson gson = new Gson();
        var data = gson.fromJson(json, Object.class);

        String expression = Files.readString(expressionPath, Charset.defaultCharset());
        var expr = jsonata(expression);
        //The intermediate form (should) be of the form of a JList of LinkedHashMaps
        //The maps are String -> Object (but we know the expected type of object)
        //for each given key String
        var result = (JList<LinkedHashMap<String, Object>>) expr.evaluate(data);
        return result.stream()
                .map(JSONataMapper::fromLinkedHashMap)
                .collect(Collectors.toList());

    }
}

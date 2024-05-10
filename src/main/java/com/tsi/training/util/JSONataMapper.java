package com.tsi.training.util;

import com.dashjoin.jsonata.Jsonata;
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
import java.util.stream.Stream;

import static com.dashjoin.jsonata.Jsonata.jsonata;
import static com.tsi.training.util.Util.getDoubleOrNull;
import static com.tsi.training.util.Util.getStringOrNull;

/**
 * Logic for mapping an input json file to a list of orderDTOs and a list of part descriptions
 */
public class JSONataMapper {

    private static final Gson gson = new Gson();
    private static final Path expressionPath = Path.of("./src/main/resources/expressions/JSONataExpression.txt");
    private static final String PARTS_KEY = "Parts";

    private static OrderDTO fromLinkedHashMap(LinkedHashMap<String, Object> input){
        OrderDTO output = new OrderDTO();

        output.setDealerDescription(getStringOrNull(input, "DealerDescription"));
        output.setOrderReference(getStringOrNull(input, "OrderReference"));
        output.setOrderDate(getStringOrNull(input, "OrderDate"));
        output.setCustomerName(getStringOrNull(input, "CustomerName"));
        output.setMobile(getStringOrNull(input, "Mobile"));
        output.setPostcode(getStringOrNull(input, "Postcode"));


        Stream<LinkedHashMap<String, Object>> partsStream = getPartsStream(input);
        output.setParts(partsStream
                .map(part -> {
                    OrderItemDTO partDTO = new OrderItemDTO();
                    partDTO.setPrice(getDoubleOrNull(part, "Price"));
                    partDTO.setProposedDate(getStringOrNull(part, "ProposedDate"));
                    partDTO.setPartDescription(getStringOrNull(part, "PartDescription"));
                    return partDTO;
                }).collect(Collectors.toList()));

        return output;
    }


    @SuppressWarnings("unchecked")
    private static Stream<LinkedHashMap<String, Object>> getPartsStream(LinkedHashMap<String, Object> input) {
        Object parts = input.get(PARTS_KEY);
        if (parts instanceof JList) {
            return ((JList<LinkedHashMap<String, Object>>) parts).stream();
        } else if (parts instanceof LinkedHashMap) {
            return Stream.of((LinkedHashMap<String, Object>) parts);
        } else {
            return Stream.empty(); // Return an empty stream if parts is neither JList nor LinkedHashMap
        }
    }

    /**
     * Maps the json contained withing a provided file using JSONata to the
     * ProcessResponse format
     * @param dataFilePath The path to the file to be read as the input
     * @return A POJO containing a list of orders and a list of part desciptions
     */
    public static ProcessResponse processJSON(Path dataFilePath) throws IOException {
        //read the json from file and convert with gson
        String input = Files.readString(dataFilePath, Charset.defaultCharset());
        var data = gson.fromJson(input, new TypeToken<LinkedHashMap<String, Object>>(){}.getType());

        //read and compile the jsonata from file
        String expression = Files.readString(expressionPath, Charset.defaultCharset());
        Jsonata expr = jsonata(expression);

        //apply the jsonata to the data
        var result = (LinkedHashMap<String, Object>) expr.evaluate(data);

        //split out the orders and the parts
        var rawOrders = (JList<LinkedHashMap<String, Object>>) result.get("orders");
        List<String> parts = (JList<String>) result.get("parts");

        //process the orders into DTO form
        List<OrderDTO> orders =  rawOrders.stream()
                .map(JSONataMapper::fromLinkedHashMap)
                .collect(Collectors.toList());

        return new ProcessResponse(orders, parts);
    }

}




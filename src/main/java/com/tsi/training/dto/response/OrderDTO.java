package com.tsi.training.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private String dealerDescription;
    private String orderReference;
    private String orderDate;
    private String customerName;
    private String mobile;
    private String postcode;
    private List<OrderItemDTO> parts;

}

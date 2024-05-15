package com.tsi.training.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String dealerDescription;
    private String orderReference;
    private String orderDate;
    private String customerName;
    private String mobile;
    private String postcode;
    private List<OrderItemDTO> parts;
}

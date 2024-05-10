package com.tsi.training.dto.response;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Double price;
    private String proposedDate;
    private String partDescription;
}
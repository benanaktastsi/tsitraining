package com.tsi.training.dto.response;

import lombok.Data;

@Data
public class OrderItemDTO {
    private double price;
    private String proposedDate;
    private String partDescription;
}
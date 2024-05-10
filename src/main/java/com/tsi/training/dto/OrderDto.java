package com.tsi.training.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {

    private Long id;
    private Date orderDate;
    private List<PartId> partIds;
    private Long customerId;
    private Long dealerId;
    private String externalId;
    private SystemType systemType;
}

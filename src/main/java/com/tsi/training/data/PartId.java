package com.tsi.training.data;

import lombok.Data;

import java.util.Date;

@Data
public class PartId {

    private Long partId;
    private String externalId;
    private SystemType systemType;
    private Date proposedDeliveryDate;

}

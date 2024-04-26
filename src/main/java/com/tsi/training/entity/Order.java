package com.tsi.training.entity;

import com.tsi.training.data.PartId;
import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Data
public class Order {

    private String reference;
    private String orderDate;
    private List<PartId> partIds;
    private Long customerId;
    private Long dealerId;
}

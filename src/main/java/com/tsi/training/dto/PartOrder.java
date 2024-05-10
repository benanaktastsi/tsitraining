package com.tsi.training.dto;

import com.tsi.training.dto.response.OrderDTO;
import com.tsi.training.dto.response.OrderItemDTO;
import lombok.Data;

@Data
public class PartOrder{
    private double price;
    private String proposedDate;
    private String partDescription;
    private String dealerDescription;
    private String orderReference;
    private String orderDate;
    private String customerName;
    private String mobile;
    private String postcode;

    public PartOrder(OrderDTO order, OrderItemDTO part){
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
}

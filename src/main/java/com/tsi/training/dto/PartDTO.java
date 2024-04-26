package com.tsi.training.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PartDTO extends BaseDTO {

    private String description;
    private Double price;
}

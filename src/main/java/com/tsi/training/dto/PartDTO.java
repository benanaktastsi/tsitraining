package com.tsi.training.dto;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartDTO extends BaseDTO {

    private Long id;
    private String description;
    private Double price;
}

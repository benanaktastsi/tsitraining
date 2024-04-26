package com.tsi.training.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Dealer {

    @Id
    private Long id;
    private String description;
}

package com.tsi.training.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Part extends BaseEntity {
    @Id
    public Long getId() {
        return id;
    }

    private String description;
    private Double price;

}

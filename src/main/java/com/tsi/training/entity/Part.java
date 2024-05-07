package com.tsi.training.entity;

import com.tsi.training.request.PartRequest;
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

    public Part() {}

    public Part(PartRequest request) {
        this.description = request.description;
        this.price = request.price;
    }
}

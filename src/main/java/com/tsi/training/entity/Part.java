package com.tsi.training.entity;

import com.tsi.training.request.PartRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Part extends BaseEntity {
    @Id
    private Long id;
    private String description;
    private Double price;

    public Part(PartRequest request) {
        this.description = request.description;
        this.price = request.price;
    }
}

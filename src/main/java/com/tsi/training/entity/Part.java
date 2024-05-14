package com.tsi.training.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Part extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Causes problems with the Cucumber tests as it uses the ID
    private Long id;

    @Column(unique = true)
    private String description;

    private Double price;
}

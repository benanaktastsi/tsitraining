package com.tsi.training.entity;

import com.tsi.training.data.Contact;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Customer {

    @Id
    private Long id;
    private String firstName;
    private String surname;
    private String addressLine1;
    private String addressLine2;
    private String postCode;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Contact contact;

}

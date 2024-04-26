package com.tsi.training.entity;

import com.tsi.training.data.Contact;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Customer {

    @Id private Long id;
        private String firstName;
        private String surname;
        private String addressLine1;
        private String addressLine2;
        private String postCode;
        private Contact contact;

}

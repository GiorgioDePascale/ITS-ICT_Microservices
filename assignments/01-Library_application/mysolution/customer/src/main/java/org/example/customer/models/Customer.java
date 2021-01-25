package org.example.customer.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;


@Document
public class Customer {

    @Id
    private String customerId;
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String surname;


}


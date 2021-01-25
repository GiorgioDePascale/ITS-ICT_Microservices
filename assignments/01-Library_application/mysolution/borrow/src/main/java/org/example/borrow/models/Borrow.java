package org.example.borrow.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "borrows")
public class Borrow {

    @Id
    private Long borrowId;

    private String customerId;

}

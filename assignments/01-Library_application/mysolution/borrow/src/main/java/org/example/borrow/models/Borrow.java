package org.example.borrow.models;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "borrowings")
public class Borrow {

    @Id
    private Long borrowId;
    private String startBorrow;
    private String endBorrow;
    private Long bookId;
    private Long customerId;
    private String notify;

}

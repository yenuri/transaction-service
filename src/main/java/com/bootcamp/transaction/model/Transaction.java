package com.bootcamp.transaction.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "transaction")
public class Transaction {

    @Id
    private String id;
    private Customer customer;
    private String transactionType;
    private Product product;
    private Double amount;
    private Date transactionDate;
    private String status;
}

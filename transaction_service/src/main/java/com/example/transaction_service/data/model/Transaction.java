package com.example.transaction_service.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.UUID;

@Document(collection = "transaction")
@Data
public class Transaction {

    @Id
    private String id;

    private Double sum;

    private Long account_to;

    private Long account_from;

    private String currency_shortname;

    private ZonedDateTime dateTime;

    private String expense_category;

    private Boolean limit_exceeded;

    private Double current_currency_sum;

}

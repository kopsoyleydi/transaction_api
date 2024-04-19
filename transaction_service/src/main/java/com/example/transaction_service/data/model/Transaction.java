package com.example.transaction_service.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    private String id;

    private Double sum;

    private Long account_to;

    private Long account_from;

    private String currency_shortname;

    private LocalDateTime dateTime;

    private String expense_category;

    private Boolean limit_exceeded;

    private Double current_currency_sum;

    public Transaction(String number, double v) {
    }

    public Transaction(String number, LocalDateTime localDateTime) {
    }
}

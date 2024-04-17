package com.example.transaction_service.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.ZonedDateTime;
import java.util.UUID;

@Table("transaction")
@Data
public class Transaction {

    @Id
    private UUID id;

    @Column("sum")
    private Double sum;

    @Column("account_to")
    private Long account_to;

    @Column("account_from")
    private Long account_from;

    @Column("currency_shortname")
    private String currency_shortname;


    @Column("dateTime")
    private ZonedDateTime dateTime;

    @Column("expense_category")
    private String expense_category;

    @Column("limit_exceeded")
    private Boolean limit_exceeded;

    @Column("current_currency_sum")
    private Double current_currency_sum;

}

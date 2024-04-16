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

    @Column("limit_datetime")
    private ZonedDateTime limit_datetime;

    @Column("limit_currency_shortname")
    private String limit_currency_shortname;

    @Column("limit_sum")
    private Double limit_sum;

    @Column("dateTime")
    private ZonedDateTime dateTime;

    @Column("expense_category")
    private String expense_category;

    @Column("limit_exceeded")
    private Long limit_exceeded;

    @Column("remaining_limit")
    private Long remaining_limit;

    @Column("current_currency_sum")
    private Double current_currency_sum;

}

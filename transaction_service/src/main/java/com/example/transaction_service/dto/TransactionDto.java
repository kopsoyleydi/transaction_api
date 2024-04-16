package com.example.transaction_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private String transactionId;

    private Double sum;

    private Long account_to;

    private Long account_from;

    private String currency_shortname;

    private String limit_datetime;

    private String limit_currency_shortname;

    private Double limit_sum;

    private ZonedDateTime dateTime;

    private String expense_category;

    private Long limit_exceeded;

    private Long remaining_limit;

    private Double current_currency_sum;
}

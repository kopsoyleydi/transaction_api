package com.example.transaction_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private UUID id;

    private Double sum;

    private Long account_to;

    private Long account_from;

    private String currency_shortname;

    private ZonedDateTime dateTime;

    private String expense_category;

    private Boolean limit_exceeded;

    private Double current_currency_sum;
}

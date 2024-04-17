package com.example.transaction_service.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("currency")
@Data
public class Currency {

    @PrimaryKey
    @Id
    private UUID id;

    @Column("currencyCode")
    private String currencyCode;

    @Column("currencyAmount")
    private Double currencyAmount;
}

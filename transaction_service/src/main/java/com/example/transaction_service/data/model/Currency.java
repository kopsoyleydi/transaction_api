package com.example.transaction_service.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "currency")
public class Currency {

    @Id
    private String id;

    private String currencyCode;

    private Double currencyAmount;

    public Currency(String currencyCode, Double currencyAmount){
        this.currencyCode = currencyCode;
        this.currencyAmount = currencyAmount;
    }
}

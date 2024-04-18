package com.example.transaction_service.dto.body;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MinusAmountFromLimit {

    private Long accountFrom;

    private Double sum;
}

package com.example.transaction_service.dto.body;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MinusAmountFromLimit {

    private Long accountFrom;

    private Double sum;
}

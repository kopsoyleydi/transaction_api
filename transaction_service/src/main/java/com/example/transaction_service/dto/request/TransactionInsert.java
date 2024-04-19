package com.example.transaction_service.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionInsert {

    private Long account_from;

    private Long account_to;

    private String currency_shortname;

    private Double sum;

    private String expense_category;

}

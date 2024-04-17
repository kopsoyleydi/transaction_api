package com.example.transaction_service.dto.body;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class TransactionInsert {

    private Long account_from;

    private Long account_to;

    private String currency_shortname;

    private Double sum;

    private String expense_category;

}

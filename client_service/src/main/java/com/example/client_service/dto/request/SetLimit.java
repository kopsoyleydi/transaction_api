package com.example.client_service.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetLimit {

    private String currency_code;

    private Double limit;
}

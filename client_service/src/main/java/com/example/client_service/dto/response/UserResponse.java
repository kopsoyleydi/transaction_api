package com.example.client_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;

    private String name;

    private Double limit_sum;

    private Double remaining_limit;

    private ZonedDateTime limit_datetime;

    private String limit_currency_shortname;

    private String surname;

    private String clientIin;

    private String birthDate;

    private String address;

    private Double balance;

}

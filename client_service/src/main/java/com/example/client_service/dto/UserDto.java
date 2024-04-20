package com.example.client_service.dto;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String name;

    private Double limit_sum;


    private LocalDateTime limit_datetime;

    private String limit_currency_shortname;

    private String surname;

    private String clientIin;

    private String birthDate;

    private String address;

    private Double balance;

    @ManyToOne
    private CityDto city;

    @ManyToMany
    private List<PermissionDto> permissions;
}

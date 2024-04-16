package com.example.client_service.dto;

import com.example.client_service.model.City;
import com.example.client_service.model.Permission;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "limit_sum")
    private Double limit_sum;

    @Column(name = "limit_datetime")
    private ZonedDateTime limit_datetime;

    @Column(name = "limit_currency_shortname")
    private String limit_currency_shortname;

    @Column(name = "surname")
    private String surname;

    @Column(name = "clientIin")
    private String clientIin;

    @Column(name = "birthDate")
    private String birthDate;

    @Column(name = "address")
    private String address;

    @Column(name = "balance")
    private Double balance;

    @ManyToOne
    private CityDto city;

    @ManyToMany
    private List<PermissionDto> permissions;
}

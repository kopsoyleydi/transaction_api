package com.example.client_service.model;

import com.example.client_service.model.enums.Usertype;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "t_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "limit_sum")
    private Double limit_sum;


    @Column(name = "limit_datetime")
    private LocalDateTime limit_datetime;

    @Column(name = "limit_currency_shortname")
    private String limit_currency_shortname;

    @Column(name = "surname")
    private String surname;

    @Column(name = "client_iin")
    private String clientIin;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "address")
    private String address;

    @Column(name = "balance")
    private Double balance;

    @ManyToOne
    private City city;

    @ManyToMany
    private List<Permission> permissions;

}

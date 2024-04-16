package com.example.client_service.model;

import com.example.client_service.model.enums.Usertype;
import jakarta.persistence.*;

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Usertype usertype;
}

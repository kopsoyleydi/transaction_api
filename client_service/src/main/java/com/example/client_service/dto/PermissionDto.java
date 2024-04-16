package com.example.client_service.dto;

import com.example.client_service.model.enums.Usertype;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {
    private Long id;

    private Usertype usertype;
}

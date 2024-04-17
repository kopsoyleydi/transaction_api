package com.example.client_service.service;

import com.example.client_service.data.repointer.UserRepoInter;

import com.example.client_service.dto.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepoInter userRepoInter;

    private final UserMapper userMapper;

    public ResponseEntity<?> getRemainingLimit(Long accountFrom){
        try {
            return ResponseEntity.ok(userRepoInter.getRemainingLimit(accountFrom));
        }
        catch (DataAccessException e){
            return ResponseEntity.internalServerError().body("Ошибка при взаимодействием с базой ");
        }
    }

    public Double getAccountLimit(Long accountFrom){
        return userRepoInter.getAccountLimit(accountFrom);
    }





}

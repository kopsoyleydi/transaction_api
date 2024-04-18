package com.example.client_service.service;

import com.example.client_service.data.repointer.UserRepoInter;

import com.example.client_service.dto.UserDto;
import com.example.client_service.dto.mapper.UserMapper;
import com.example.client_service.dto.response.TransactionDto;
import com.example.client_service.dto.response.UserResponse;
import com.example.client_service.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepoInter userRepoInter;

    private final UserMapper userMapper;

    private final UserUtil userUtil;

    public ResponseEntity<?> getRemainingLimit(Long accountFrom){
        try {
            return ResponseEntity.ok(userRepoInter.getRemainingLimit(accountFrom));
        }
        catch (DataAccessException e){
            return ResponseEntity.internalServerError().body("Ошибка при взаимодействием с базой ");
        }
    }

    public UserResponse getAccount(Long account){
        try {
            UserDto userDto = userMapper.toDto(userRepoInter.getUserById(account));
            return new UserResponse(userDto.getId()
                    , userDto.getName(), userDto.getLimit_sum(), userDto.getLimit_sum(), userDto.getLimit_datetime(),
                    userDto.getLimit_currency_shortname(), userDto.getSurname()
                    , userDto.getClientIin(), userDto.getBirthDate(), userDto.getAddress(), userDto.getBalance());
        }
        catch (DataAccessException e){
            return new UserResponse();
        }
    }


    public Double getAccountLimit(Long accountFrom){
        return userRepoInter.getAccountLimit(accountFrom);
    }

    public ResponseEntity<?> setNewLimit(Long userId, Double limit, String currency){
        try {
            return ResponseEntity.ok(userRepoInter.setNewLimit(userId, limit, currency));
        }
        catch (DataAccessException e){
            return ResponseEntity.internalServerError().body("Ошибка при взаимодействием с базой ");
        }
    }

    public List<TransactionDto> getAccountTransactions(Long userId){
        return userUtil.getUsersByAccountFromList(userId).block();
    }





}

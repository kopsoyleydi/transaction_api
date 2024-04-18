package com.example.client_service.service;

import com.example.client_service.data.repointer.UserRepoInter;

import com.example.client_service.dto.UserDto;
import com.example.client_service.dto.mapper.UserMapper;
import com.example.client_service.dto.response.TransactionDto;
import com.example.client_service.dto.response.UserResponse;
import com.example.client_service.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public ResponseEntity<?> getRemainingLimit(Long accountFrom){
        try {
            return ResponseEntity.ok(userRepoInter.getRemainingLimit(accountFrom));
        }
        catch (DataAccessException e){
            logger.error(e.getLocalizedMessage());
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
            logger.error(e.getMessage());
            return new UserResponse();
        }
    }


    public Double getAccountLimit(Long accountFrom){
        return userRepoInter.getAccountLimit(accountFrom);
    }

    public ResponseEntity<?> setNewLimit(Long userId, Double limit, String currency){
        try {
            userRepoInter.setNewLimit(userId, limit, currency);
            logger.info("Limit was successfully set");
            return ResponseEntity.ok(userRepoInter.getAccountLimit(userId));
        }
        catch (DataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().body("Ошибка при взаимодействием с базой ");
        }
    }

    public List<TransactionDto> getAccountTransactions(Long userId) throws Exception {
        try {
            return userUtil.getUsersByAccountFromList(userId).block();
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception();
        }
    }


}

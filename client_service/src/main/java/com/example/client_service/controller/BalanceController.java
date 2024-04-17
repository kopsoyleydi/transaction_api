package com.example.client_service.controller;

import com.example.client_service.dto.request.MinusAmountFromLimit;
import com.example.client_service.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/balance")
public class BalanceController {

    private final BalanceService balanceService;

    public ResponseEntity<?> minus(@RequestBody MinusAmountFromLimit minusAmountFromLimit){
        try {
            return ResponseEntity.ok(balanceService
                    .minusBalance(minusAmountFromLimit.getAccountFrom(), minusAmountFromLimit.getSum()));
        }
        catch (DataAccessException e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

}

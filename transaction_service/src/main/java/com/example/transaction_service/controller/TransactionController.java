package com.example.transaction_service.controller;

import com.example.transaction_service.dto.body.TransactionInsert;
import com.example.transaction_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(value = "/insert")
    public ResponseEntity<?> insertTransaction(@RequestBody TransactionInsert transactionInsert){
        try {
            return ResponseEntity.ok(transactionService.transactionInsert(transactionInsert));
        }
        catch (Exception e){
            return null;
        }
    }
}

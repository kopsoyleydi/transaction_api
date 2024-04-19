package com.example.transaction_service.controller;

import com.example.transaction_service.dto.request.TransactionInsert;
import com.example.transaction_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(value = "/insert")
    public ResponseEntity<?> insertTransaction(@RequestBody TransactionInsert transactionInsert) throws Exception {
        return ResponseEntity.ok(transactionService.transactionInsert(transactionInsert));
    }

    @GetMapping(value = "list/limit/{accountFrom}")
    public ResponseEntity<?> getAccountTransactions(@PathVariable Long accountFrom) throws Exception {
        try {
            return ResponseEntity.ok(transactionService.getAccountTransaction(accountFrom, true));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    @GetMapping(value = "/list/{accountFrom}")
    public ResponseEntity<?> getAccountLimitTransactions(@PathVariable Long accountFrom) throws Exception {
        try {
            return ResponseEntity.ok(transactionService.getAccountTransaction(accountFrom, false));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }
}

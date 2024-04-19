package com.example.transaction_service.controller;

import com.example.transaction_service.data.model.Transaction;
import com.example.transaction_service.dto.body.TransactionInsert;
import com.example.transaction_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public List<Transaction> getAccountTransactions(@PathVariable Long accountFrom) throws Exception {
        try {
            return transactionService.getAccountTransaction(accountFrom, true);
        }
        catch (Exception e){
            return List.of();
        }
    }

    @GetMapping(value = "/list/{accountFrom}")
    public List<Transaction> getAccountLimitTransactions(@PathVariable Long accountFrom) throws Exception {
        try {
            return transactionService.getAccountTransaction(accountFrom, false);
        }
        catch (Exception e){
            return List.of();
        }
    }
}

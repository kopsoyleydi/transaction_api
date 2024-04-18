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
@RequestMapping(value = "/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(value = "/insert")
    public ResponseEntity<?> insertTransaction(@RequestBody TransactionInsert transactionInsert) throws Exception {
        return ResponseEntity.ok(transactionService.transactionInsert(transactionInsert));
    }

    @GetMapping(value = "/getTransactions/{accountFrom}")
    public List<Transaction> getAccountTransactions(@PathVariable Long accountFrom) throws Exception {
        return transactionService.getAccountTransaction(accountFrom);
    }
}

package com.example.transaction_service.service;

import com.example.transaction_service.data.repointer.TransactionRepoInter;
import com.example.transaction_service.dto.body.TransactionInsert;
import com.example.transaction_service.dto.mapper.TransactionMapper;
import com.example.transaction_service.util.TransactionUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionUtil transactionUtil;

    private final TransactionRepoInter transactionRepoInter;

    private final TransactionMapper transactionMapper;

    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);

    public ResponseEntity<?> transactionInsert(TransactionInsert transactionInsert){
        try {
            return ResponseEntity.ok(transactionMapper.toDto(transactionRepoInter
                    .insert(transactionMapper
                            .toModel(transactionUtil
                                    .parseTransaction(transactionInsert)))));
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }


}

package com.example.transaction_service.service;

import com.example.transaction_service.data.repointer.TransactionRepoInter;
import com.example.transaction_service.dto.TransactionDto;
import com.example.transaction_service.dto.body.TransactionInsert;
import com.example.transaction_service.dto.mapper.TransactionMapper;
import com.example.transaction_service.util.TransactionLimitUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionLimitUtil transactionLimitUtil;

    private final TransactionRepoInter transactionRepoInter;

    private final TransactionMapper transactionMapper;

    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);

    public TransactionDto transactionInsert(TransactionInsert transactionInsert) throws Exception {
        try {
            return transactionMapper.toDto(
                    transactionRepoInter.insert(transactionLimitUtil
                                    .transaction(transactionInsert)));
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception("Something went wrong");
        }
    }

}

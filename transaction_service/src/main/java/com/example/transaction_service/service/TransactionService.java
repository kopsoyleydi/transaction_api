package com.example.transaction_service.service;

import com.example.transaction_service.data.model.Transaction;
import com.example.transaction_service.data.repointer.TransactionRepoInter;
import com.example.transaction_service.dto.TransactionDto;
import com.example.transaction_service.dto.body.TransactionInsert;
import com.example.transaction_service.dto.body.UserDto;
import com.example.transaction_service.dto.mapper.TransactionMapper;
import com.example.transaction_service.util.TransactionInsertMapper;
import com.example.transaction_service.util.TransactionLimitUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class TransactionService {

    @Autowired
    private TransactionLimitUtil transactionLimitUtil;

    @Autowired
    private TransactionRepoInter transactionRepoInter;

    @Autowired
    private TransactionMapper transactionMapper;
    

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

    public List<Transaction> getAccountTransaction(Long accountFrom) throws Exception {
        try {
            UserDto userDto = transactionLimitUtil.getUserByAccountFrom(accountFrom).block();
            assert userDto != null;
            List<Transaction> transactions = transactionRepoInter.getByAllTransactionAccountFrom(accountFrom);
            Map<Boolean, List<Transaction>> partitionedTransactions = transactions.stream()
                    .collect(Collectors.partitioningBy(f -> f.getDateTime().isAfter(userDto.getLimit_datetime())));
            transactions.clear();
            transactions.addAll(partitionedTransactions.get(true));
            return transactions;
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception("Something went wrong");
        }
    }

}

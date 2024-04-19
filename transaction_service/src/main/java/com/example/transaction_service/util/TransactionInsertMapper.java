package com.example.transaction_service.util;

import com.example.transaction_service.data.model.Transaction;
import com.example.transaction_service.data.repointer.CurrencyRepoInter;
import com.example.transaction_service.dto.CurrencyDto;
import com.example.transaction_service.dto.request.TransactionInsert;
import com.example.transaction_service.dto.mapper.CurrencyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.example.transaction_service.util.TransactionUtil.currencySum;

@Component
@RequiredArgsConstructor
public class TransactionInsertMapper {

    private final CurrencyMapper currencyMapper;

    private final CurrencyRepoInter currencyRepoInter;

    public Transaction parse(TransactionInsert transactionInsert){
        Transaction transaction = new Transaction();
        transaction.setAccount_from(transactionInsert.getAccount_from());
        transaction.setAccount_to(transactionInsert.getAccount_to());
        transaction.setSum(transactionInsert.getSum());
        CurrencyDto currencyDto = currencyMapper.toDto(
                currencyRepoInter.getByCurrencyCode(
                        transactionInsert.getCurrency_shortname()));
        transaction.setCurrent_currency_sum(currencySum(transactionInsert.getSum(), currencyDto.getCurrencyAmount()));
        transaction.setDateTime(LocalDateTime.now(ZoneId.of("Asia/Tashkent")));
        transaction.setCurrency_shortname(transactionInsert.getCurrency_shortname());
        transaction.setExpense_category(transactionInsert.getExpense_category());
        return transaction;
    }
}

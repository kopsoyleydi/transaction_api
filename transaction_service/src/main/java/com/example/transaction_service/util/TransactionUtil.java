package com.example.transaction_service.util;

import com.example.transaction_service.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class TransactionUtil {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);

    public static Double currencySum(Double sum, Double currencyAmount){
        try {
            return sum * currencyAmount;
        }
        catch (Exception e){
            logger.error("Error in Transaction Service, method: currencySum");
            return null;
        }
    }

//    @Deprecated
//    public TransactionDto parseTransaction(TransactionInsert transactionInsert){
//        try {
//            TransactionDto transactionDto = new TransactionDto();
//            transactionDto.setAccount_from(transactionInsert.getAccount_from());
//            transactionDto.setAccount_to(transactionInsert.getAccount_to());
//            CurrencyDto currencyDto = currencyMapper.toDto(
//                    currencyRepoInter.getByCurrencyCode(
//                            transactionInsert.getCurrency_shortname()));
//            transactionDto.setCurrent_currency_sum(currencySum(
//                    transactionInsert.getSum(), currencyDto.getCurrencyAmount()));
//            transactionDto.setDateTime(ZonedDateTime.now());
//            transactionDto.setCurrency_shortname(transactionInsert.getCurrency_shortname());
//            transactionDto.setExpense_category(transactionInsert.getExpense_category());
//            transactionDto.setLimit_exceeded(transactionLimitUtil
//                    .SetLimitExceededToTransaction(transactionInsert.getAccount_from()));
//            return transactionDto;
//        }
//        catch (Exception e){
//            logger.error(e.getMessage());
//            return null;
//        }
//    }

}

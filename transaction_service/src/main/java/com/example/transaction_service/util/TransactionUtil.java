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
            int test = 0;
            return sum * (1 / currencyAmount);
        }
        catch (Exception e){
            logger.error("Error in Transaction Service, method: currencySum");
            return null;
        }
    }

}

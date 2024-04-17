package com.example.transaction_service.service;

import com.example.transaction_service.data.repointer.CurrencyRepoInter;
import com.example.transaction_service.dto.mapper.CurrencyMapper;
import com.example.transaction_service.util.LoadCurrencyInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepoInter currencyRepoInter;

    private final CurrencyMapper currencyMapper;

    private final LoadCurrencyInfo loadCurrencyInfo;

    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);

    public ResponseEntity<?> getCurrencyAmount(String code){
        try {
            return ResponseEntity.ok(currencyMapper.toDto(
                    currencyRepoInter.getByCurrencyCode(code)));
        }
        catch (Exception e){
            logger.error("Error in Currency Service, method: getCurrencyAmount");
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    public ResponseEntity<?> loadCurrencies(){
        try {
            loadCurrencyInfo.loadInfoFromExchangeRate();
            return ResponseEntity.ok("Filling was success");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }
}

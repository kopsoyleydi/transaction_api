package com.example.transaction_service.util;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CurrencyInfoUpdater {

    private final LoadCurrencyInfo loadCurrencyInfo;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateAirQualityData() {
        loadCurrencyInfo.loadInfoFromExchangeRate();
        System.out.println("Currency data updated successfully.");
    }
}

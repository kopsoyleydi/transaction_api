package com.example.transaction_service.util;

import com.example.transaction_service.service.LoadCurrencyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CurrencyInfoUpdater {

    private final LoadCurrencyInfo loadCurrencyInfo;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateAirQualityData() {
        try {
            loadCurrencyInfo.loadInfoFromExchangeRate();
            System.out.println("Currency data updated successfully.");
        } catch (IOException e) {
            System.err.println("Failed to update currency data: " + e.getMessage());
        }
    }
}

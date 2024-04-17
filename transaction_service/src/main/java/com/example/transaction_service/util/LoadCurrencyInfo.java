package com.example.transaction_service.util;

import com.example.transaction_service.data.model.Currency;
import com.example.transaction_service.data.repointer.CurrencyRepoInter;
import com.example.transaction_service.dto.body.Exchange;
import com.example.transaction_service.service.CurrencyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LoadCurrencyInfo {

    @Autowired
    private CurrencyRepoInter currencyRepoInter;

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);

    public LoadCurrencyInfo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public void loadInfoFromExchangeRate() {
        try {
            String apiUrl = "https://v6.exchangerate-api.com/v6/dc9484e428a232e8b53e9284/latest/USD";
            Exchange exchangeRates = restTemplate.getForObject(apiUrl, Exchange.class);

            if (exchangeRates == null || exchangeRates.getConversion_rates() == null) {
                logger.error("Failed to fetch exchange rates from API");
                return;
            }
            List<Currency> currencyList = new ArrayList<>();
            Map<String, Double> conversionRates = exchangeRates.getConversion_rates();
            for (Map.Entry<String, Double> entry : conversionRates.entrySet()) {
                String currencyCode = entry.getKey();
                Double rate = entry.getValue();
                currencyList.add(new Currency(currencyCode, rate));
                currencyRepoInter.insert(new Currency(currencyCode, rate));
                logger.info("Currency Code: {}, Rate: {}", currencyCode, rate);
            }
            int test = 0;
            //currencyRepoInter.insertAllCurrencies(currencyList);
            logger.info("Currency info was successfully loaded and saved");
        } catch (RestClientException e) {
            logger.error("Error while fetching or processing currency info from API: {}", e.getMessage(), e);
        }
    }
}

package com.example.transaction_service.service;

import com.example.transaction_service.dto.body.Exchange;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Service
public class LoadCurrencyInfo {
    public void loadInfoFromExchangeRate() throws IOException {
        String apiUrl = "https://v6.exchangerate-api.com/v6/dc9484e428a232e8b53e9284/latest/USD";

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        connection.disconnect();
        if (!Integer.toString(responseCode).startsWith("2")) {
            throw new IOException("HTTP response code: " + responseCode);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Exchange exchangeRates = objectMapper.readValue(response.toString(), Exchange.class);

        Map<String, Double> conversionRates = exchangeRates.getConversion_rates();
        for (Map.Entry<String, Double> entry : conversionRates.entrySet()) {
            String currencyCode = entry.getKey();
            Double rate = entry.getValue();
            System.out.println("Currency Code: " + currencyCode + ", Rate: " + rate);
        }
    }
}

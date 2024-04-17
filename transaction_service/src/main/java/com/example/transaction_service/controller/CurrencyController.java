package com.example.transaction_service.controller;

import com.example.transaction_service.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping(value = "/loadInfo")
    public ResponseEntity<?> loadInfo() {
        return currencyService.loadCurrencies();
    }
}

package com.example.transaction_service.controller;

import com.example.transaction_service.service.LoadCurrencyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

    private final LoadCurrencyInfo loadCurrencyInfo;

    @PostMapping(value = "/loadInfo")
    public ResponseEntity<?> loadInfo(){
        try {
            loadCurrencyInfo.loadInfoFromExchangeRate();
            return ResponseEntity.ok("Success");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }
}

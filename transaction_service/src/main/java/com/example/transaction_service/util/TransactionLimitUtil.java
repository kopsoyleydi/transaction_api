package com.example.transaction_service.util;

import com.example.transaction_service.dto.body.MinusAmountFromLimit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class TransactionLimitUtil {

    private final WebClient webClientForUser;

    private final ObjectMapper objectMapper;
    public TransactionLimitUtil(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        webClientForUser = webClientBuilder.baseUrl("http://localhost:9001").build();
        this.objectMapper = objectMapper;
    }

    public boolean SetLimitExceededToTransaction(Long accountFrom){
        AtomicBoolean ans = new AtomicBoolean(false);
        webClientForUser.get()
                .uri("/api/user/getRemainingLimit")
                .retrieve()
                .bodyToMono(Double.class)
                .subscribe(responseBody -> {
                    if(responseBody >= 0){
                        ans.set(true);
                    }
                });
        return ans.get();
    }

    public boolean minusAmountFromLimit(Long accountFrom, Double sum) throws JsonProcessingException {
        AtomicBoolean ans = new AtomicBoolean(false);
        String requestBody = objectMapper.writeValueAsString(
                MinusAmountFromLimit.builder().accountFrom(accountFrom).sum(sum)
                        .build());
        webClientForUser.put()
                .uri("/api/resource")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(Double.class)
                .subscribe(responseBody -> {
                    if (!responseBody.isNaN()){
                        ans.set(true);
                    }
                });
        return ans.get();
    }

    public Double getLimitFromAccount(Long accountFrom){
        AtomicReference<Double> ans = new AtomicReference<>(0.0);
        webClientForUser.get()
                .uri("/api/user/getAccountLimit")
                .retrieve()
                .bodyToMono(Double.class)
                .subscribe(responseBody -> {
                    if(responseBody >= 0){
                        ans.set(responseBody);
                    }
                });
        return ans.get();
    }
}

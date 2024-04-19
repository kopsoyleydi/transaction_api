package com.example.client_service.util;

import com.example.client_service.dto.response.TransactionDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class UserUtil {

    private final WebClient webClientForTransactions;


    public UserUtil(WebClient.Builder webClientBuilder) {
        webClientForTransactions = webClientBuilder.baseUrl("http://localhost:9002").build();
    }


    public Mono<List<TransactionDto>> getUsersByAccountFromList(Long accountFrom, boolean type) {
        String str = type ? "limit/" : "";
        return webClientForTransactions.get()
                .uri("/transaction/list/" + str + accountFrom)
                .retrieve()
                .bodyToFlux(TransactionDto.class) // Заменяем bodyToMono на bodyToFlux
                .collectList();
    }

    public Mono<Double> getAmountByCurrencyCode(String code) {
        return webClientForTransactions.get()
                .uri("/currency/amount/" + code)
                .retrieve()
                .bodyToMono(Double.class); // Заменяем bodyToMono на bodyToFlux
    }
}

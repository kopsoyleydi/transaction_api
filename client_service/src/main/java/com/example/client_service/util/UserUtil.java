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


    public Mono<List<TransactionDto>> getUsersByAccountFromList(Long accountFrom) {
        return webClientForTransactions.get()
                .uri("/api/transaction/getTransactions/" + accountFrom)
                .retrieve()
                .bodyToMono(TransactionDto.class)
                .flatMap(userDto -> Mono.just(List.of(userDto)));
    }




}

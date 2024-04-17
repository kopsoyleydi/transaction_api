package com.example.transaction_service.util;

import com.example.transaction_service.data.model.Transaction;
import com.example.transaction_service.data.repointer.CurrencyRepoInter;
import com.example.transaction_service.data.repointer.TransactionRepoInter;
import com.example.transaction_service.dto.CurrencyDto;
import com.example.transaction_service.dto.body.MinusAmountFromLimit;
import com.example.transaction_service.dto.body.TransactionInsert;
import com.example.transaction_service.dto.body.UserDto;
import com.example.transaction_service.dto.mapper.CurrencyMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.transaction_service.util.TransactionUtil.currencySum;

@Component
public class TransactionLimitUtil {

    private final WebClient webClientForUser;

    @Autowired
    private TransactionRepoInter transactionRepoInter;

    private final ObjectMapper objectMapper;

    @Autowired
    private CurrencyMapper currencyMapper;

    @Autowired
    private CurrencyRepoInter currencyRepoInter;
    public TransactionLimitUtil(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        webClientForUser = webClientBuilder.baseUrl("http://localhost:9001").build();
        this.objectMapper = objectMapper;
    }

    public boolean SetLimitExceededToTransaction(Long accountFrom){
        AtomicBoolean ans = new AtomicBoolean(false);
        webClientForUser.get()
                .uri("/api/user/getRemainingLimit/" + accountFrom)
                .retrieve()
                .bodyToMono(Double.class)
                .subscribe(responseBody -> {
                    if(responseBody >= 0){
                        ans.set(true);
                    }
                });
        return ans.get();
    }

    public Mono<UserDto> getUserByAccountFrom(Long accountFrom) {
        return webClientForUser.get()
                .uri("/api/user/" + accountFrom)
                .retrieve()
                .bodyToMono(UserDto.class);
    }

    public boolean minusAmountFromLimit(Long accountFrom, Double sum) throws JsonProcessingException {
        AtomicBoolean ans = new AtomicBoolean(false);
        String requestBody = objectMapper.writeValueAsString(
                MinusAmountFromLimit.builder().accountFrom(accountFrom).sum(sum)
                        .build());
        webClientForUser.put()
                .uri("/api/balance/minus")
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

    public Transaction transaction(TransactionInsert transactionInsert){
        Transaction transactionDto = new Transaction();
        UserDto userDto = getUserByAccountFrom(transactionInsert.getAccount_from()).block();
        List<Transaction> transactionList = transactionRepoInter.getByAllTransactionAccountFrom(1L);
        assert userDto != null;
        double limit = userDto.getLimit_sum();
        List<Transaction> inTime = new ArrayList<>();
        double transactionSum = 0.0;
        boolean limit_exceeded = false;
        for (Transaction value : transactionList) {
            if (value.getDateTime().isAfter(userDto.getLimit_datetime().toLocalDateTime())
                    && userDto.getLimit_datetime().isBefore(ZonedDateTime.now())) {
                inTime.add(value);
            }
        }
        int test = 0;
        transactionSum = inTime.stream().mapToDouble(Transaction::getSum).sum();
        if(transactionSum + transactionInsert.getSum() >= limit){
            limit_exceeded = true;
        }
        Optional<Transaction> lastTransaction = inTime.stream()
                .max(Comparator.comparing(Transaction::getDateTime));
        transactionDto.setAccount_from(transactionInsert.getAccount_from());
        transactionDto.setAccount_to(transactionInsert.getAccount_to());
        transactionDto.setSum(transactionInsert.getSum());
        CurrencyDto currencyDto = currencyMapper.toDto(
                currencyRepoInter.getByCurrencyCode(
                        transactionInsert.getCurrency_shortname()));
        transactionDto.setCurrent_currency_sum(currencySum(
                transactionInsert.getSum(), currencyDto.getCurrencyAmount()));
        transactionDto.setDateTime(LocalDateTime.now());
        transactionDto.setCurrency_shortname(transactionInsert.getCurrency_shortname());
        transactionDto.setExpense_category(transactionInsert.getExpense_category());
        transactionDto.setLimit_exceeded(limit_exceeded);
        return transactionDto;
    }
}

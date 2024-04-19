package com.example.transaction_service.util;

import com.example.transaction_service.data.model.Transaction;
import com.example.transaction_service.data.repointer.CurrencyRepoInter;
import com.example.transaction_service.data.repointer.TransactionRepoInter;
import com.example.transaction_service.dto.body.MinusAmountFromLimit;
import com.example.transaction_service.dto.request.TransactionInsert;
import com.example.transaction_service.dto.body.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.xml.transform.TransformerException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
public class TransactionLimitUtil {

    private final WebClient webClientForUser;

    @Autowired
    private TransactionRepoInter transactionRepoInter;

    @Autowired
    private TransactionInsertMapper transactionInsertMapper;

    @Autowired
    private CurrencyRepoInter currencyRepoInter;

    private final ObjectMapper objectMapper;

    public TransactionLimitUtil(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        webClientForUser = webClientBuilder.baseUrl("http://localhost:9001").build();
        this.objectMapper = objectMapper;
    }

//    public boolean SetLimitExceededToTransaction(Long accountFrom){
//        AtomicBoolean ans = new AtomicBoolean(false);
//        webClientForUser.get()
//                .uri("/api/user/getRemainingLimit/" + accountFrom)
//                .retrieve()
//                .bodyToMono(Double.class)
//                .subscribe(responseBody -> {
//                    if(responseBody >= 0){
//                        ans.set(true);
//                    }
//                });
//        return ans.get();
//    }

    public Mono<UserDto> getUserByAccountFrom(Long accountFrom) {
        return webClientForUser.get()
                .uri("/api/user/" + accountFrom)
                .retrieve()
                .bodyToMono(UserDto.class);
    }

    public Mono<Double> minusAmountFromLimit(Long account, Double sum) {
        MinusAmountFromLimit minusAmountFromLimit = new MinusAmountFromLimit(account, sum);
        return webClientForUser.put()
                .uri("/api/balance/minus")
                .body(Mono.just(minusAmountFromLimit), MinusAmountFromLimit.class)
                .retrieve()
                .bodyToMono(Double.class);
    }

    private Mono<? extends Throwable> handleClientError(ClientResponse clientResponse) {
        return Mono.error(new TransformerException("Employee not found"));
    }

//    public Double getLimitFromAccount(Long accountFrom){
//        AtomicReference<Double> ans = new AtomicReference<>(0.0);
//        webClientForUser.get()
//                .uri("/api/user/getAccountLimit")
//                .retrieve()
//                .bodyToMono(Double.class)
//                .subscribe(responseBody -> {
//                    if(responseBody >= 0){
//                        ans.set(responseBody);
//                    }
//                });
//        int test = 0;
//        return ans.get();
//    }

    public Transaction transaction(TransactionInsert transactionInsert) throws JsonProcessingException {
        UserDto userDto = getUserByAccountFrom(transactionInsert.getAccount_from()).block();

        List<Transaction> transactionList = transactionRepoInter
                .getByAllTransactionAccountFrom(transactionInsert.getAccount_from());

        /** Переменные
         * **/
        assert userDto != null;
        double limit = userDto.getLimit_sum() ;
        List<Transaction> inTime = new ArrayList<>();
        double transactionSum = 0.0;
        boolean limit_exceeded = false;
        double checkCur = 1.0;
        for (Transaction value : transactionList) {
            if (value.getDateTime().isAfter(userDto.getLimit_datetime())
                    && userDto.getLimit_datetime().isBefore(LocalDateTime.now())) {
                inTime.add(value);
            }
        }

        transactionSum = inTime.stream().mapToDouble(Transaction::getCurrent_currency_sum).sum();

        Double currency = currencyRepoInter.getByCurrencyCode(userDto.getLimit_currency_shortname()).getCurrencyAmount();

        checkCur = Objects.equals(transactionInsert.getCurrency_shortname().toUpperCase(), "KZT") ? 1.0 : currency;

        if(transactionSum + (transactionInsert.getSum() * (1 / checkCur )) > (limit * (1 / currency))){
            limit_exceeded = true;
        }

        Transaction transaction = transactionInsertMapper.parse(transactionInsert);
        Double balance = minusAmountFromLimit(
                transaction.getAccount_from(), (transactionInsert.getSum() * (1 / checkCur ))).block();
        transaction.setSum(transaction.getSum());

        transaction.setLimit_exceeded(limit_exceeded);
        return transaction;
    }
}

package com.example.transaction_service.service;

import com.example.transaction_service.data.model.Transaction;
import com.example.transaction_service.data.repointer.TransactionRepoInter;
import com.example.transaction_service.dto.TransactionDto;
import com.example.transaction_service.dto.body.TransactionInsert;
import com.example.transaction_service.dto.body.UserDto;
import com.example.transaction_service.util.TransactionInsertMapper;
import com.example.transaction_service.util.TransactionLimitUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TransactionServiceTest {

    @Mock
    private TransactionRepoInter transactionRepoInter;

    @Mock
    private TransactionInsertMapper transactionInsertMapper;

    @Mock
    private TransactionLimitUtil transactionLimitUtil;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void transactionTest() throws Exception {
        TransactionInsert transactionInsert = new TransactionInsert();
        transactionInsert.setAccount_from(123456L);
        transactionInsert.setSum(100.0);
        Transaction transaction = new Transaction();
        when(transactionInsertMapper.parse(transactionInsert)).thenReturn(transaction);

        UserDto userDto = new UserDto();
        userDto.setLimit_sum(500.0);
        userDto.setLimit_datetime(LocalDateTime.now().minusHours(1));
        when(transactionLimitUtil.getUserByAccountFrom(transactionInsert.getAccount_from())).thenReturn(Mono.just(userDto));

        List<Transaction> transactionList = Arrays.asList(
                new Transaction("1", 50.0),
                new Transaction("2", 60.0)
        );
        when(transactionRepoInter.getByAllTransactionAccountFrom(transactionInsert.getAccount_from())).thenReturn(transactionList);
        TransactionDto result = transactionService.transactionInsert(transactionInsert);
        assertEquals(110.0, result.getSum());
        assertEquals(true, result.getLimit_exceeded());
    }

    @Test
    void getAccountTransactionTest() throws Exception {
        Long accountFrom = 123456L;
        UserDto userDto = new UserDto();
        userDto.setLimit_datetime(LocalDateTime.now().minusHours(1));
        when(transactionLimitUtil.getUserByAccountFrom(accountFrom)).thenReturn(Mono.just(userDto));

        List<Transaction> transactions = Arrays.asList(
                new Transaction("1", LocalDateTime.now().minusHours(2)),
                new Transaction("2", LocalDateTime.now().minusMinutes(30)),
                new Transaction("3", LocalDateTime.now().plusMinutes(30))
        );
        when(transactionRepoInter.getByAllTransactionAccountFrom(accountFrom)).thenReturn(transactions);
        List<Transaction> result = transactionService.getAccountTransaction(accountFrom);
        assertEquals(1, result.size());
        assertEquals("2", result.get(0).getId());
    }
}

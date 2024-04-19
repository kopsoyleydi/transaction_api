package com.example.transaction_service.migration;

import com.example.transaction_service.data.model.Transaction;
import com.example.transaction_service.data.repository.TransactionRepository;
import com.example.transaction_service.util.LoadCurrencyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MongoMigration {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LoadCurrencyInfo loadCurrencyInfo;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {

        /**
         *  Автозапуск LoadCurrencyInfo при каждом запуске сервиса
         *  **/
        loadCurrencyInfo.loadInfoFromExchangeRate();

        if (transactionRepository.count() > 0) {
            System.out.println("Данные в MongoDB уже существуют. Инициализация пропущена.");
            return;
        }

        /**
         *  Генерация тестовых транзакции
         *  **/
        generateTransactions();


        System.out.println("Данные успешно инициализированы в MongoDB.");
    }

    private void generateTransactions() {
        Transaction transaction1 = new Transaction(
                "1", 1000.0, 123456789L, 1L, "USD",
                LocalDateTime.now(), "Food", false,
                450000.0);
        Transaction transaction2 = new Transaction(
                "2", 1000.0, 987654321L, 1L, "USD",
                LocalDateTime.now(), "Shopping", false,
                450000.0);
        Transaction transaction3 = new Transaction(
                "3", 1000.0, 123456789L, 1L, "USD",
                LocalDateTime.now(), "Transport", false,
                450000.0);
        Transaction transaction4 = new Transaction(
                "4", 1000.0, 888888888L, 2L, "USD",
                LocalDateTime.now(), "Entertainment", false,
                450000.0);
        Transaction transaction5 = new Transaction(
                "5", 1000.0, 123456789L, 2L, "USD",
                LocalDateTime.now(), "Utilities", false,
                450000.0);

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
        transactionRepository.save(transaction3);
        transactionRepository.save(transaction4);
        transactionRepository.save(transaction5);
    }
}

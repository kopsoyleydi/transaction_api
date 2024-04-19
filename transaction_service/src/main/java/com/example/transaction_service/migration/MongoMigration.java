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
                "1", 100.0, 123456789L, 1L, "USD",
                LocalDateTime.now(), "Food", false, 90.0,
                10.0);
        Transaction transaction2 = new Transaction(
                "2", 200.0, 987654321L, 1L, "USD",
                LocalDateTime.now(), "Shopping", false, 180.0,
                20.0);
        Transaction transaction3 = new Transaction(
                "3", 150.0, 123456789L, 1L, "EUR",
                LocalDateTime.now(), "Transport", false, 135.0,
                15.0);
        Transaction transaction4 = new Transaction(
                "4", 75.0, 888888888L, 2L, "GBP",
                LocalDateTime.now(), "Entertainment", false, 67.5,
                7.5);
        Transaction transaction5 = new Transaction(
                "5", 300.0, 123456789L, 2L, "USD",
                LocalDateTime.now(), "Utilities", false, 270.0,
                30.0);

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
        transactionRepository.save(transaction3);
        transactionRepository.save(transaction4);
        transactionRepository.save(transaction5);
    }
}

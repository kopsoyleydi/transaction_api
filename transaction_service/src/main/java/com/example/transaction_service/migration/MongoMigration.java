package com.example.transaction_service.migration;

import com.example.transaction_service.data.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MongoMigration {

    @Autowired
    private TransactionRepository transactionRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        if (transactionRepository.count() > 0) {
            System.out.println("Данные в MongoDB уже существуют. Инициализация пропущена.");
            return;
        }

        System.out.println("Данные успешно инициализированы в MongoDB.");
    }
}

package com.example.transaction_service.data.repository;

import com.example.transaction_service.data.model.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface CurrencyRepository extends MongoRepository<Currency, UUID> {

    public Currency findByCurrencyCode(String code);

}

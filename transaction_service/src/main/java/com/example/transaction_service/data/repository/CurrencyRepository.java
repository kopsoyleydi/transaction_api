package com.example.transaction_service.data.repository;

import com.example.transaction_service.data.model.Currency;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface CurrencyRepository extends CassandraRepository<Currency, UUID> {

    @Query("select * from currency where currencycode = :code")
    public Currency findByCurrencyCode(String code);

}

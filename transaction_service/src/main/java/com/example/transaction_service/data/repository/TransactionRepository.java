package com.example.transaction_service.data.repository;

import com.example.transaction_service.data.model.Currency;
import com.example.transaction_service.data.model.Transaction;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface TransactionRepository extends CassandraRepository<Transaction, UUID> {

    @Query("select * from transaction t where t.account_from = :id")
    List<Transaction> findAllByAccount_from(Long id);

    @Query("select * from transaction t where t.account_from = :from and t.account_to = :to")
    List<Transaction> findAllByAccount_fromAndAccount_to(Long from, Long to);
}

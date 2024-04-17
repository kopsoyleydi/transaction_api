package com.example.transaction_service.data.repository;

import com.example.transaction_service.data.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface TransactionRepository extends MongoRepository<Transaction, UUID> {

    @Query("{ 'account_from' : ?0 }")
    List<Transaction> findAllByAccountFrom(Long id);

    @Query("select * from transaction t where t.account_from = :from and t.account_to = :to")
    List<Transaction> findAllByAccount_fromAndAccount_to(Long from, Long to);
}

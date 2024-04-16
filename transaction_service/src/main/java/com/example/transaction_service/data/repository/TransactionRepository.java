package com.example.transaction_service.data.repository;

import com.example.transaction_service.data.model.Transaction;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface TransactionRepository extends CassandraRepository<Transaction, UUID> {
}

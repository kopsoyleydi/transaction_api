package com.example.transaction_service.data;

import com.example.transaction_service.data.model.Transaction;
import com.example.transaction_service.data.repointer.TransactionRepoInter;
import com.example.transaction_service.data.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionRepoImpl implements TransactionRepoInter {

    private final TransactionRepository transactionRepository;
    @Override
    public Transaction insert(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction update(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getByAllTransactionAccountFrom(Long id) {
        return transactionRepository.findAllByAccount_from(id);
    }

    @Override
    public List<Transaction> getAllTransactionAccountFromAndAccountTo(Long from, Long to) {
        return transactionRepository.findAllByAccount_fromAndAccount_to(from, to);
    }
}

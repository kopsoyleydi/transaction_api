package com.example.transaction_service.data.repointer;


import com.example.transaction_service.data.model.Transaction;

import java.util.List;

public interface TransactionRepoInter {

    Transaction insert(Transaction transaction);

    Transaction update(Transaction transaction);

    List<Transaction> getByAllTransactionAccountFrom(Long id);


}

package com.github.exys666.transactions.storage;

import com.github.exys666.transactions.exceptions.TransactionConflictException;
import com.github.exys666.transactions.exceptions.TransactionNotFoundException;
import com.github.exys666.transactions.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class TransactionRepository {

    private final ConcurrentMap<Long, Transaction> transactions = new ConcurrentHashMap<>();

    public Transaction get(long id) {
        Transaction transaction = transactions.get(id);
        if(transaction == null) {
            throw new TransactionNotFoundException(transaction);
        }
        return  transaction;
    }

    public synchronized void add(Transaction transaction) {
        if(transactions.containsKey(transaction.getId())) {
            throw new TransactionConflictException(transaction);
        }
        transactions.put(transaction.getId(), transaction);
    }

    public synchronized void clear() {
        transactions.clear();
    }
}

package com.github.exys666.transactions.storage;

import com.github.exys666.transactions.exceptions.TransactionConflictException;
import com.github.exys666.transactions.exceptions.TransactionNotFoundException;
import com.github.exys666.transactions.model.Transaction;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multimaps;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.google.common.collect.Multimaps.synchronizedListMultimap;

@Component
public class TransactionRepository {

    private final ConcurrentMap<Long, Transaction> transactions = new ConcurrentHashMap<>();
    private final Multimap<String, Transaction> typesMap = synchronizedListMultimap(MultimapBuilder.hashKeys().arrayListValues().build());

    public Transaction get(long id) {
        Transaction transaction = transactions.get(id);
        if(transaction == null) {
            throw new TransactionNotFoundException(id);
        }
        return  transaction;
    }

    public synchronized void add(Transaction transaction) {
        if(transactions.containsKey(transaction.getId())) {
            throw new TransactionConflictException(transaction);
        }
        transactions.put(transaction.getId(), transaction);
        typesMap.put(transaction.getType(), transaction);
    }

    public Collection<Transaction> findByType(String type) {
        return typesMap.get(type);
    }

    public synchronized void clear() {
        transactions.clear();
    }
}

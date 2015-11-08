package com.github.exys666.transactions.exceptions;

import com.github.exys666.transactions.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Transaction with given ID already exists")
public class TransactionConflictException extends RuntimeException {

    public TransactionConflictException(Transaction transaction) {
        super(format("Transaction with ID = %d already exists", transaction.getId()));
    }
}

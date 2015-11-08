package com.github.exys666.transactions.exceptions;

import com.github.exys666.transactions.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Transaction Not Found")
public class TransactionNotFoundException extends RuntimeException{

    public TransactionNotFoundException(long id) {
        super(format("Transaction with ID = %d not found", id));
    }
}

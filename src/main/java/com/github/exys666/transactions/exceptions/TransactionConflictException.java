package com.github.exys666.transactions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Transaction with given ID already exists")
public class TransactionConflictException extends RuntimeException {
}

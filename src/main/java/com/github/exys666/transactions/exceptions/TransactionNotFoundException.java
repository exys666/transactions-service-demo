package com.github.exys666.transactions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Transaction Not Found")
public class TransactionNotFoundException extends RuntimeException{
}

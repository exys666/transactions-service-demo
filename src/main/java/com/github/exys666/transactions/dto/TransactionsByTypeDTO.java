package com.github.exys666.transactions.dto;


import com.github.exys666.transactions.model.Transaction;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor
public class TransactionsByTypeDTO extends ArrayList<Long> {

    public TransactionsByTypeDTO(List<Long> ids) {
        this.addAll(ids);
    }

    public static TransactionsByTypeDTO create(List<Transaction> transactions) {
        List<Long> ids = transactions.stream().map(Transaction::getId).collect(toList());
        return new TransactionsByTypeDTO(ids);
    }

}

package com.github.exys666.transactions.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.exys666.transactions.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private double amount;
    private String type;
    private Long parentId;


    public TransactionDTO(Transaction transaction) {
        this.type = transaction.getType();
        this.amount = transaction.getAmount();

        if (transaction.hasParent()) {
            this.parentId = transaction.getParent().getId();
        }
    }

    @JsonIgnore
    public boolean hasParent() {
        return parentId != null;
    }
}

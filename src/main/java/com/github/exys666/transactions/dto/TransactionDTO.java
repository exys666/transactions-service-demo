package com.github.exys666.transactions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class TransactionDTO {

    private double amount;
    private String type;
    private Long parentId;
}

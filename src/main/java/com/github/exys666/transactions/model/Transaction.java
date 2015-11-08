package com.github.exys666.transactions.model;


import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Transaction {

    @Getter
    private final long id;
    @Getter
    private final double amount;
    @Getter
    private double sum;
    @Getter
    private final String type;
    @Getter
    private final Transaction parent;
    private final List<Transaction> children;

    @Builder
    private Transaction(long id, String type, double amount, Transaction parent) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.sum = amount;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public boolean hasParent() {
        return parent != null;
    }

    public synchronized void addChild(Transaction transaction) {
        children.add(transaction);
        updateSum();
    }

    private void updateSum() {
        sum = calculateSum();
        if(parent != null) {
            parent.updateSum();
        }
    }

    private double calculateSum() {
        return amount + children.stream().map(Transaction::getSum).reduce(0.0, Double::sum);
    }

}

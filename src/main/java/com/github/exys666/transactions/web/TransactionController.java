package com.github.exys666.transactions.web;

import com.github.exys666.transactions.dto.TransactionDTO;
import com.github.exys666.transactions.model.Transaction;
import com.github.exys666.transactions.storage.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepo;

    @RequestMapping(method = PUT, value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void putTransaction(@PathVariable("id") long id, @RequestBody TransactionDTO dto) {
        Transaction.TransactionBuilder builder = Transaction.builder();

        builder
                .id(id)
                .type(dto.getType())
                .amount(dto.getAmount());

        if (dto.hasParent()) {
            builder.parent(transactionRepo.get(dto.getParentId()));
        }

        Transaction transaction = builder.build();
        transactionRepo.add(transaction);

        if(transaction.hasParent()) {
            transaction.getParent().addChild(transaction);
        }
    }

    @RequestMapping(method = GET, value = "/{id}")
    public TransactionDTO getTransaction(@PathVariable("id") long id) {
        return new TransactionDTO(transactionRepo.get(id));
    }
}

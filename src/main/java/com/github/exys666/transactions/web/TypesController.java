package com.github.exys666.transactions.web;

import com.github.exys666.transactions.dto.TransactionsByTypeDTO;
import com.github.exys666.transactions.storage.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/types")
public class TypesController {

    @Autowired
    private TransactionRepository transactionRepo;

    @RequestMapping(method = GET, value="/{type}")
    public TransactionsByTypeDTO getByType(@PathVariable("type") String type) {
        return TransactionsByTypeDTO.create(transactionRepo.findByType(type));
    }
}

package com.github.exys666.transactions.web;

import com.github.exys666.transactions.dto.SumDTO;
import com.github.exys666.transactions.storage.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/sum")
public class SumController {

    @Autowired
    private TransactionRepository transactionRepo;

    @RequestMapping(method = GET, value = "/{id}")
    public SumDTO getSum(@PathVariable("id") long id) {
        return new SumDTO(transactionRepo.get(id).getSum());
    }
}

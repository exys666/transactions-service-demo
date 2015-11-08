package com.github.exys666.transactions.web;

import com.github.exys666.transactions.dto.TransactionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @RequestMapping(method = PUT, value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void putTransaction(@PathVariable("id") long id, @RequestBody TransactionDTO dto) {

    }

    @RequestMapping(method = GET, value = "/{id}")
    public TransactionDTO getTransaction(@PathVariable("id") long id) {
        return null;
    }
}

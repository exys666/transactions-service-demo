package com.github.exys666.transactions.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @RequestMapping(method = PUT, value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void putTransaction(@PathVariable("id") long id) {

    }

    @RequestMapping(method = GET, value = "/{id}")
    public void getTransaction(@PathVariable("id") long id) {

    }
}

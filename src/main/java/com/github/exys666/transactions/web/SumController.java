package com.github.exys666.transactions.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/sum")
public class SumController {

    @RequestMapping(method = GET, value = "/{id}")
    public void getSum(@PathVariable("id") long id) {

    }
}

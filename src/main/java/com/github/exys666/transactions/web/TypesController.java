package com.github.exys666.transactions.web;

import com.github.exys666.transactions.dto.TransactionsByTypeDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/types")
public class TypesController {

    @RequestMapping(method = GET, value="/{type}")
    public TransactionsByTypeDTO getByType(@PathVariable("type") String type) {
        return null;
    }
}

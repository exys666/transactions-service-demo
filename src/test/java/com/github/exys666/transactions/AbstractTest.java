package com.github.exys666.transactions;

import com.github.exys666.transactions.dto.TransactionDTO;
import com.github.exys666.transactions.storage.TransactionRepository;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.http.ContentType.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
public abstract class AbstractTest {

    @Value("${server.port}")
    private int port;

    @Autowired
    private TransactionRepository repository;

    @After
    public void clean() {
        repository.clear();
    }

    public RequestSpecification given() {
        return RestAssured.given().port(port);
    }

    public Response createTransaction(long id, TransactionDTO dto) {
        return given()
                .contentType(JSON)
                .body(dto)
                .when().put("/transactionsservice/transaction/" + id);
    }
}

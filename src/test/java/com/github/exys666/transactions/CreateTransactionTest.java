package com.github.exys666.transactions;


import com.github.exys666.transactions.dto.TransactionDTO;
import com.github.exys666.transactions.model.Transaction;
import com.jayway.restassured.path.json.JsonPath;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateTransactionTest extends AbstractTest {


    @Test
    public void shouldCreateTransaction() {
        TransactionDTO dto = new TransactionDTO(10.0, "type", null);

        createTransaction(1L, dto).then().statusCode(201);

        // @formatter:off
        JsonPath response = when()
                                .get("/transactionsservice/transaction/1")
                            .then()
                                .statusCode(200)
                            .and()
                                .extract().body().jsonPath();
        // @formatter:on

        assertThat(response.getDouble("amount")).isEqualTo(dto.getAmount());
        assertThat(response.getString("type")).isEqualTo(dto.getType());
    }

    @Test
    public void shouldRespondWithConflictWhenTransactionAlreadyExists() {
        TransactionDTO dto = new TransactionDTO(20.0, "type", null);

        createTransaction(1L, dto).then().statusCode(201);
        createTransaction(1L, dto).then().statusCode(409);
    }

    @Test
    public void shouldRespondWithNotFoundWhenTransactionParentNotExists() {
        TransactionDTO dto = new TransactionDTO(30.0, "type", 0L);
        createTransaction(1L, dto).then().statusCode(404);
    }

    @Test
    public void shouldRespondWithBadRequestWhenAmountMissing() {
        TransactionDTO dto = new TransactionDTO(null, "type", null);
        createTransaction(1L, dto).then().statusCode(400);
    }

    @Test
    public void shouldRespondWithBadRequestWhenTypeMissing() {
        TransactionDTO dto = new TransactionDTO(15.0, null, null);
        createTransaction(1L, dto).then().statusCode(400);
    }


}

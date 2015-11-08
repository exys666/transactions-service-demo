package com.github.exys666.transactions;


import com.github.exys666.transactions.dto.TransactionDTO;
import com.jayway.restassured.path.json.JsonPath;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateTransactionTest extends AbstractTest {


    @Test
    public void shouldCreateTransaction() {
        TransactionDTO dto = new TransactionDTO(10, "type", null);

        createTransaction(1L, dto).then().statusCode(201);

        // @formatter:off
        JsonPath response = given()
                            .when()
                                .get("/transactionsservice/transaction/1")
                            .then()
                                .statusCode(200)
                                .extract().body().jsonPath();
        // @formatter:on

        assertThat(response.getDouble("amount")).isEqualTo(dto.getAmount());
        assertThat(response.getString("type")).isEqualTo(dto.getType());
    }

    @Test
    public void shouldRespondWithConflictWhenTransactionAlreadyExists() {
        TransactionDTO dto = new TransactionDTO(20, "type", null);

        createTransaction(1L, dto).then().statusCode(201);

        createTransaction(1L, dto).then().statusCode(409);
    }

    @Test
    public void shouldRespondWithNotFoundWhenTransactionParentNotExists() {
        TransactionDTO dto = new TransactionDTO(30, "type", 0L);

        createTransaction(1L, dto).then().statusCode(404);
    }


}

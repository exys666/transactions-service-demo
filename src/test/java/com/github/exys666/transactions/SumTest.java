package com.github.exys666.transactions;

import com.github.exys666.transactions.dto.TransactionDTO;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SumTest extends AbstractTest {

    @Test
    public void shouldBeEqualToAmountIfNoChildrenTransactions() {
        TransactionDTO dto = new TransactionDTO(10.0, "someType", null);

        createTransaction(1L, dto).then().statusCode(201);

        assertThat(sum(1L)).isEqualTo(dto.getAmount());
    }

    @Test
    public void shouldCountChildrenAmount() {
        TransactionDTO parent = new TransactionDTO(10.0, "type1", null);
        TransactionDTO child1 = new TransactionDTO(20.0, "type2", 1L);
        TransactionDTO child2 = new TransactionDTO(30.0, "type3", 1L);

        createTransaction(1L, parent).then().statusCode(201);
        createTransaction(2L, child1).then().statusCode(201);
        createTransaction(3L, child2).then().statusCode(201);

        assertThat(sum(1L)).isEqualTo(parent.getAmount() + child1.getAmount() + child2.getAmount());
    }

    @Test
    public void shouldCountGrandChildrenAmount() {
        TransactionDTO parent = new TransactionDTO(10.0, "type1", null);
        TransactionDTO child1 = new TransactionDTO(20.0, "type2", 1L);
        TransactionDTO child2 = new TransactionDTO(30.0, "type3", 2L);

        createTransaction(1L, parent).then().statusCode(201);
        createTransaction(2L, child1).then().statusCode(201);
        createTransaction(3L, child2).then().statusCode(201);

        assertThat(sum(1L)).isEqualTo(parent.getAmount() + child1.getAmount() + child2.getAmount());
    }

    @Test
    public void shouldRespondWithNotFoundWhenCallSumOfNotExistingTransaction() {
        // @formatter:off
        when()
            .get("/transactionsservice/sum/1")
        .then()
            .statusCode(404);
        // @formatter:on
    }

    private double sum(long id) {
        // @formatter:off
        return when()
            .get("/transactionsservice/sum/" + id)
        .then()
            .statusCode(200)
        .and()
            .extract().body().jsonPath().getDouble("sum");
        // @formatter:on
    }
}

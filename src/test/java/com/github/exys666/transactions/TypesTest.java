package com.github.exys666.transactions;

import com.github.exys666.transactions.dto.TransactionDTO;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TypesTest extends AbstractTest {

    @Test
    public void shouldReturnEmptyListItNoTransactionOfGivenTypeExists() {
        assertThat(listTransactionsWithType("someType")).isEmpty();
    }

    @Test
    public void shouldContainsAllTransactionsOfGivenType() {
        createTransaction(1L, new TransactionDTO(10.0, "type1", null));
        createTransaction(2L, new TransactionDTO(20.0, "type2", null));
        createTransaction(3L, new TransactionDTO(30.0, "type1", 1L));

        assertThat(listTransactionsWithType("type1")).containsOnly(1L, 3L);
    }

    private List<Long> listTransactionsWithType(String type) {
        // @formatter:off
        return when()
            .get("/transactionsservice/types/" + type)
        .then()
            .statusCode(200)
        .and()
            .extract().body().jsonPath().getList("$", Long.class);
        // @formatter:on
    }
}

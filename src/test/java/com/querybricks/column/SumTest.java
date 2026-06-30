package com.querybricks.column;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class SumTest {
    private final Column<Integer> column = new Sum<>(
        new RawColumn<>("amount")
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            this.column.sql(),
            Matchers.equalTo("SUM(amount)")
        );
    }
}

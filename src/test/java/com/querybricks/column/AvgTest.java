package com.querybricks.column;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class AvgTest {
    private final Column<Double> column = new Avg<>(
        new RawColumn<>("rating")
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            this.column.sql(),
            Matchers.equalTo("AVG(rating)")
        );
    }
}

package com.querybricks.column;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class CountTest {
    private final Column<Long> column = new Count<>(
        new RawColumn<>("id")
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            this.column.sql(),
            Matchers.equalTo("COUNT(id)")
        );
    }
}

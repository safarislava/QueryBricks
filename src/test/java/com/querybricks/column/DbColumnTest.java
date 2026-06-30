package com.querybricks.column;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class DbColumnTest {
    private final Column<String> column = new DbColumn<>("username");

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            this.column.sql(),
            Matchers.equalTo("username")
        );
    }
}

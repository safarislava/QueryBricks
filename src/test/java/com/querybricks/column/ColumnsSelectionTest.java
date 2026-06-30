package com.querybricks.column;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class ColumnsSelectionTest {
    private final Columns columns = new ColumnsSelection(
        new DbColumn<>("id"),
        new DbColumn<>("username"),
        new DbColumn<>("email")
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            this.columns.sql(),
            Matchers.equalTo("id, username, email")
        );
    }
}

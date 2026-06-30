package com.querybricks.column;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class AllColumnsTest {
    private final AllColumns allColumns = new AllColumns();

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            allColumns.sql(),
            Matchers.equalTo("*")
        );
    }
}

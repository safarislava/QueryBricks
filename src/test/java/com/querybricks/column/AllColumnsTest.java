package com.querybricks.column;

import com.querybricks.table.FakeFilterableTable;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class AllColumnsTest {
    private final AllColumns allColumns = new AllColumns(
        new FakeFilterableTable("users")
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            allColumns.sql(),
            Matchers.equalTo("*")
        );
    }
}

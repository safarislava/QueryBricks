package com.querybricks.column;

import com.querybricks.table.FakeTable;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class TableColumnTest {
    private final Column<Long> column = new TableColumn<>(
        new FakeTable("users"),
        new DbColumn<>("id")
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            this.column.sql(),
            Matchers.equalTo("users.id")
        );
    }
}

package com.querybricks.table;

import com.querybricks.column.FakeColumns;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class GroupedTableTest {
    private final GroupedTable<Table> table = new GroupedTable<>(
        new FakeFilterableTable("users"),
        new FakeColumns("status")
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            table.sql(),
            Matchers.equalTo("users GROUP BY status")
        );
    }

    @Test
    void testOrigin() {
        MatcherAssert.assertThat(
            table.origin().sql(),
            Matchers.equalTo("users")
        );
    }
}

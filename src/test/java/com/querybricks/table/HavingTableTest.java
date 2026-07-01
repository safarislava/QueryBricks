package com.querybricks.table;

import com.querybricks.column.FakeColumns;
import com.querybricks.condition.FakeCondition;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class HavingTableTest {
    private final HavingTable<GroupedTable<Table>> table = new HavingTable<>(
        new GroupedTable<>(
            new FakeFilterableTable("users"),
            new FakeColumns("status")
        ),
        new FakeCondition("COUNT(id) > 10")
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            table.sql(),
            Matchers.equalTo("users GROUP BY status HAVING COUNT(id) > 10")
        );
    }

    @Test
    void testOrigin() {
        MatcherAssert.assertThat(
            table.origin().sql(),
            Matchers.equalTo("users GROUP BY status")
        );
    }
}

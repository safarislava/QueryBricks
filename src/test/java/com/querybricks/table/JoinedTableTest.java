package com.querybricks.table;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class JoinedTableTest {
    private final BinaryTable<Table, Table> table = new JoinedTable<>(
        new FakeFilterableTable("users"),
        new FakeFilterableTable("orders"),
        new FakeJoinRule("INNER JOIN %s ON users.id = orders.user_id")
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            table.sql(),
            Matchers.equalTo("users INNER JOIN orders ON users.id = orders.user_id")
        );
    }

    @Test
    void testLeft() {
        MatcherAssert.assertThat(
            table.left().sql(),
            Matchers.equalTo("users")
        );
    }

    @Test
    void testRight() {
        MatcherAssert.assertThat(
            table.right().sql(),
            Matchers.equalTo("orders")
        );
    }
}

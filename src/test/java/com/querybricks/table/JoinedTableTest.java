package com.querybricks.table;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class JoinedTableTest {

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            new JoinedTable<>(
                new FakeTable("users"),
                new FakeTable("orders"),
                new FakeJoinRule("INNER JOIN %s ON users.id = orders.user_id")
            ).sql(),
            Matchers.equalTo("users INNER JOIN orders ON users.id = orders.user_id")
        );
    }
}

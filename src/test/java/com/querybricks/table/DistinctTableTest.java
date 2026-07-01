package com.querybricks.table;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class DistinctTableTest {
    private final DistinctTable<Table> table = new DistinctTable<>(
        new FakeFilterableTable("users")
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            table.sql(),
            Matchers.equalTo("DISTINCT users")
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

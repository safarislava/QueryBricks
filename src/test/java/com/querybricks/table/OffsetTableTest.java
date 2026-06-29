package com.querybricks.table;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class OffsetTableTest {
    private final OffsetTable<FakeTable> table = new OffsetTable<>(
        new FakeTable("users"),
        20
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            table.sql(),
            Matchers.equalTo("users OFFSET 20")
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

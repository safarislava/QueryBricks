package com.querybricks.query;

import com.querybricks.column.FakeColumns;
import com.querybricks.table.FakeTable;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class SelectQueryTest {
    private final Query query = new SelectQuery(new FakeColumns("id, username"), new FakeTable("users"));

    @Test
    void testSql() {
        MatcherAssert.assertThat(this.query.sql(), Matchers.equalTo("SELECT id, username FROM users"));
    }
}

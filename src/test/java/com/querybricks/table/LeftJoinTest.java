package com.querybricks.table;

import com.querybricks.condition.FakeCondition;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class LeftJoinTest {

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            new LeftJoin(new FakeCondition("users.id = orders.user_id"))
                .sql(new FakeTable("orders")),
            Matchers.equalTo("LEFT JOIN orders ON users.id = orders.user_id")
        );
    }
}

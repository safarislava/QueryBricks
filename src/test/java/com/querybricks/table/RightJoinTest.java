package com.querybricks.table;

import com.querybricks.condition.FakeCondition;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class RightJoinTest {

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            new RightJoin(new FakeCondition("users.id = orders.user_id"))
                .sql(new FakeTable("orders")),
            Matchers.equalTo("RIGHT JOIN orders ON users.id = orders.user_id")
        );
    }
}

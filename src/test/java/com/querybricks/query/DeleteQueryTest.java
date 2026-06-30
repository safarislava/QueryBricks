package com.querybricks.query;

import com.querybricks.column.RawColumn;
import com.querybricks.column.TableColumn;
import com.querybricks.condition.Equals;
import com.querybricks.expression.NumberLiteral;
import com.querybricks.table.FakeTable;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class DeleteQueryTest {
    @Test
    void testSql() {
        MatcherAssert.assertThat(
            new DeleteQuery(
                new FakeTable("users"),
                new Equals(
                    new TableColumn<>(new FakeTable("users"), new RawColumn<>("id")),
                    new NumberLiteral(1)
                )
            ).sql(),
            Matchers.equalTo("DELETE FROM users WHERE users.id = 1")
        );
    }
}

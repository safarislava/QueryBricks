package com.querybricks.query;

import com.querybricks.column.Column;
import com.querybricks.column.RawColumn;
import com.querybricks.column.TableColumn;
import com.querybricks.expression.NumberLiteral;
import com.querybricks.expression.StringLiteral;
import com.querybricks.table.FakeTable;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import java.util.List;

final class InsertQueryTest {
    private final Query query = new InsertQuery(
        new FakeTable("users"),
        List.of(
            new TableColumn<>(new FakeTable("users"), new RawColumn<>("id")).unbound(),
            new TableColumn<>(new FakeTable("users"), new RawColumn<>("username")).unbound()
        ),
        List.of(
            new InsertRow(
                new NumberLiteral(1),
                new StringLiteral("john")
            )
        )
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            this.query.sql(),
            Matchers.equalTo("INSERT INTO users (id, username) VALUES (1, 'john')")
        );
    }
}

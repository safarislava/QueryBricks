package com.querybricks.query;

import com.querybricks.column.RawColumn;
import com.querybricks.column.TableColumn;
import com.querybricks.condition.Equals;
import com.querybricks.expression.NumberLiteral;
import com.querybricks.expression.StringLiteral;
import com.querybricks.table.FakeFilterableTable;
import com.querybricks.table.Table;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

final class UpdateQueryTest {
    private final Table table = new FakeFilterableTable("users");
    
    @Test
    void testSql() {
        MatcherAssert.assertThat(
            new UpdateQuery(
                table,
                List.of(
                    new ColumnAssignment(
                        new TableColumn<>(table, new RawColumn<>("username")),
                        new StringLiteral("john")
                    )
                ),
                new Equals(
                    new TableColumn<>(table, new RawColumn<>("id")),
                    new NumberLiteral(1)
                )
            ).sql(),
            Matchers.equalTo("UPDATE users SET users.username = 'john' WHERE users.id = 1")
        );
    }

    @Test
    void testMultipleAssignments() {
        MatcherAssert.assertThat(
            new UpdateQuery(
                table,
                List.of(
                    new ColumnAssignment(
                        new TableColumn<>(table, new RawColumn<>("username")),
                        new StringLiteral("john")
                    ),
                    new ColumnAssignment(
                        new TableColumn<>(table, new RawColumn<>("age")),
                        new NumberLiteral(30)
                    )
                ),
                new Equals(
                    new TableColumn<>(table, new RawColumn<>("id")),
                    new NumberLiteral(1)
                )
            ).sql(),
            Matchers.equalTo("UPDATE users SET users.username = 'john', users.age = 30 WHERE users.id = 1")
        );
    }

    @Test
    void testSqlThrowsWhenNoAssignments() {
        MatcherAssert.assertThat(
            Assertions.assertThrows(
                IllegalStateException.class,
                () -> new UpdateQuery(
                    table,
                    List.of(),
                    new Equals(
                        new TableColumn<>(table, new RawColumn<>("id")),
                        new NumberLiteral(1)
                    )
                ).sql()
            ).getMessage(),
            Matchers.equalTo("Update query must specify at least one assignment")
        );
    }
}

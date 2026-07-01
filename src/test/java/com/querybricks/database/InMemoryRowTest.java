package com.querybricks.database;

import com.querybricks.column.BoundColumn;
import com.querybricks.column.RawColumn;
import com.querybricks.column.TableColumn;
import com.querybricks.table.FakeFilterableTable;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import org.h2.jdbcx.JdbcDataSource;

final class InMemoryRowTest {
    @Test
    void testValueLookup() {
        BoundColumn<String> col = new TableColumn<>(new FakeFilterableTable("users"), new RawColumn<>("username"));
        Row row = new InMemoryRow(Map.of("users.username", "john"));
        MatcherAssert.assertThat(
            row.value(col),
            Matchers.equalTo("john")
        );
    }

    @Test
    void testValueNotFoundThrows() {
        BoundColumn<String> col = new TableColumn<>(new FakeFilterableTable("users"), new RawColumn<>("username"));
        Row row = new InMemoryRow(Map.of("email", "john@example.com"));
        MatcherAssert.assertThat(
            Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> row.value(col)
            ).getMessage(),
            Matchers.equalTo("Column not found in row: users.username")
        );
    }

    @Test
    void testConstructorFromResultSet() throws Exception {
        JdbcDataSource source = new JdbcDataSource();
        source.setUrl("jdbc:h2:mem:rowtest;DB_CLOSE_DELAY=-1");
        source.setUser("sa");
        source.setPassword("");

        try (Connection conn = source.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE users (username VARCHAR(255))");
            stmt.execute("INSERT INTO users VALUES ('john')");
            try (ResultSet rs = stmt.executeQuery("SELECT username FROM users")) {
                rs.next();
                BoundColumn<String> column = new TableColumn<>(
                    new FakeFilterableTable("users"),
                    new RawColumn<>("username")
                );
                Row row = new InMemoryRow(List.of(column), rs);
                MatcherAssert.assertThat(
                    row.value(column),
                    Matchers.equalTo("john")
                );
            } finally {
                stmt.execute("DROP TABLE users");
            }
        }
    }
}

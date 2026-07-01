package com.querybricks.database;

import com.querybricks.column.BoundColumn;
import com.querybricks.column.ColumnsSelection;
import com.querybricks.column.RawColumn;
import com.querybricks.column.TableColumn;
import com.querybricks.query.ResultedQuery;
import com.querybricks.query.SelectQuery;
import com.querybricks.table.FakeFilterableTable;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;

final class DataSourcePoolTest {
    @Test
    void testSelection() throws Exception {
        DataSource source = this.createH2DataSource("selection");
        try (Connection conn = source.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE users (username VARCHAR(255))");
            stmt.execute("INSERT INTO users VALUES ('john')");
            try {
                BoundColumn<String> col = new TableColumn<>(
                    new FakeFilterableTable("users"),
                    new RawColumn<>("username")
                );
                ResultedQuery query = new SelectQuery(
                    new ColumnsSelection(col),
                    new FakeFilterableTable("users")
                );

                List<Row> rows = new DataSourcePool(source).selection(query);
                MatcherAssert.assertThat(
                    rows.getFirst().value(col),
                    Matchers.equalTo("john")
                );
            } finally {
                stmt.execute("DROP TABLE users");
            }
        }
    }

    @Test
    void testSelectionWithDuplicateColumns() throws Exception {
        DataSource source = this.createH2DataSource("duplicates");
        try (Connection conn = source.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE t1 (id VARCHAR(255))");
            stmt.execute("CREATE TABLE t2 (id VARCHAR(255))");
            stmt.execute("INSERT INTO t1 VALUES ('first')");
            stmt.execute("INSERT INTO t2 VALUES ('second')");
            try {
                TableColumn<String> col1 = new TableColumn<>(new FakeFilterableTable("t1"), new RawColumn<>("id"));
                TableColumn<String> col2 = new TableColumn<>(new FakeFilterableTable("t2"), new RawColumn<>("id"));
                ResultedQuery query = new SelectQuery(
                    new ColumnsSelection(col1, col2),
                    new FakeFilterableTable("t1 JOIN t2 ON 1=1")
                );

                List<Row> rows = new DataSourcePool(source).selection(query);
                MatcherAssert.assertThat(
                    List.of(rows.getFirst().value(col1), rows.getFirst().value(col2)),
                    Matchers.equalTo(List.of("first", "second"))
                );
            } finally {
                stmt.execute("DROP TABLE t1");
                stmt.execute("DROP TABLE t2");
            }
        }
    }

    @Test
    void testExecute() throws Exception {
        DataSource source = this.createH2DataSource("execute");
        try (Connection conn = source.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE log (message VARCHAR(255))");
            try {
                new DataSourcePool(source).execute(new com.querybricks.query.ResultedQuery() {
                    @Override
                    public String sql() {
                        return "INSERT INTO log VALUES ('test')";
                    }

                    @Override
                    public void processColumns(com.querybricks.column.ColumnsProcessor consumer) {
                    }
                });
                try (ResultSet rs = stmt.executeQuery("SELECT message FROM log")) {
                    rs.next();
                    MatcherAssert.assertThat(
                        rs.getString(1),
                        Matchers.equalTo("test")
                    );
                }
            } finally {
                stmt.execute("DROP TABLE log");
            }
        }
    }

    private DataSource createH2DataSource(String dbName) {
        JdbcDataSource source = new JdbcDataSource();
        source.setUrl("jdbc:h2:mem:" + dbName + ";DB_CLOSE_DELAY=-1");
        source.setUser("sa");
        source.setPassword("");
        return source;
    }
}

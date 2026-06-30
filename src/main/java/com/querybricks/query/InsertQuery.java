package com.querybricks.query;

import com.querybricks.QueryPart;
import com.querybricks.column.UnboundColumn;
import com.querybricks.table.Table;
import java.util.List;
import java.util.stream.Collectors;

public class InsertQuery implements Query {
    private final Table table;
    private final List<UnboundColumn<?>> columns;
    private final List<InsertRow> rows;

    public InsertQuery(Table table, List<UnboundColumn<?>> columns, List<InsertRow> rows) {
        this.table = table;
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public String sql() {
        if (this.columns.isEmpty()) {
            throw new IllegalStateException("Insert query must specify at least one column");
        }
        if (this.rows.isEmpty()) {
            throw new IllegalStateException("Insert query must have at least one row");
        }
        String columns = this.columns.stream().map(QueryPart::sql).collect(Collectors.joining(", "));
        String values = this.rows.stream().map(InsertRow::sql).collect(Collectors.joining(", "));
        return String.format("INSERT INTO %s (%s) VALUES %s", this.table.sql(), columns, values);
    }
}

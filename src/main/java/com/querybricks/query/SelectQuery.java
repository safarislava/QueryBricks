package com.querybricks.query;

import com.querybricks.column.Columns;
import com.querybricks.column.ColumnsProcessor;
import com.querybricks.table.Table;

public class SelectQuery implements ResultedQuery {
    private final Columns columns;
    private final Table table;

    public SelectQuery(Columns columns, Table table) {
        this.columns = columns;
        this.table = table;
    }

    @Override
    public String sql() {
        return String.format("SELECT %s FROM %s", this.columns.sql(), this.table.sql());
    }

    @Override
    public void processColumns(ColumnsProcessor consumer) {
        this.columns.processAll(consumer);
    }
}

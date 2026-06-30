package com.querybricks.column;

import com.querybricks.table.Table;

public class TableColumn<T> implements Column<T> {
    private final Table table;
    private final Column<T> column;

    public TableColumn(Table table, Column<T> column) {
        this.table = table;
        this.column = column;
    }

    @Override
    public String sql() {
        return String.format("%s.%s", this.table.sql(), this.column.sql());
    }
}

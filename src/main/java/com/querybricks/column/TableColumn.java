package com.querybricks.column;

import com.querybricks.table.Table;

public class TableColumn<T> implements BindedColumn<T> {
    private final Table table;
    private final UnbindedColumn<T> column;

    public TableColumn(Table table, UnbindedColumn<T> column) {
        this.table = table;
        this.column = column;
    }

    @Override
    public String sql() {
        return String.format("%s.%s", this.table.sql(), this.column.sql());
    }

    @Override
    public UnbindedColumn<T> unbinded() {
        return this.column;
    }
}

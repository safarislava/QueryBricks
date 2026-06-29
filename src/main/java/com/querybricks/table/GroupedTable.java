package com.querybricks.table;

import com.querybricks.column.Columns;

public class GroupedTable<T extends Table> implements HavableTable, WrappedTable<T> {
    private final T table;
    private final Columns columns;

    public GroupedTable(T table, Columns columns) {
        this.table = table;
        this.columns = columns;
    }

    @Override
    public String sql() {
        return String.format("%s GROUP BY %s", table.sql(), columns.sql());
    }

    @Override
    public T origin() {
        return table;
    }
}

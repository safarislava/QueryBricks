package com.querybricks.table;

import com.querybricks.Bindings;
import com.querybricks.column.Columns;

/**
 * Represents a table whose rows are grouped by one or more columns (GROUP BY clause).
 *
 * @param <T> the type of the underlying table
 */
public final class GroupedTable<T extends Table> implements HavableTable, WrappedTable<T> {
    private final T table;
    private final Columns columns;

    /**
     * Constructs a new {@code GroupedTable} with the given table and grouping columns.
     *
     * @param table the table whose rows will be grouped
     * @param columns the columns to group by
     */
    public GroupedTable(T table, Columns columns) {
        this.table = table;
        this.columns = columns;
    }

    @Override
    public String sql() {
        return String.format("%s GROUP BY %s", table.sql(), columns.sql());
    }

    @Override
    public Bindings bind(Bindings bindings) {
        return this.columns.bind(this.table.bind(bindings));
    }

    @Override
    public T origin() {
        return table;
    }
}

package com.querybricks.table;

import com.querybricks.Bindings;
import com.querybricks.condition.Condition;

/**
 * A table wrapper that applies a filter (WHERE clause) to the underlying table.
 *
 * @param <T> the type of the filterable table
 */
public final class FilteredTable<T extends FilterableTable> implements WrappedTable<T> {
    private final T table;
    private final Condition condition;

    /**
     * Constructs a new {@code FilteredTable} with the given table and filter condition.
     *
     * @param table the table to wrap and filter
     * @param condition the filter condition to apply
     */
    public FilteredTable(T table, Condition condition) {
        this.table = table;
        this.condition = condition;
    }

    @Override
    public T origin() {
        return table;
    }

    @Override
    public String sql() {
        return String.format("%s WHERE %s", table.sql(), condition.sql());
    }

    @Override
    public Bindings bind(Bindings bindings) {
        return this.condition.bind(this.table.bind(bindings));
    }
}

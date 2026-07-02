package com.querybricks.table;

import com.querybricks.Bindings;

/**
 * A decorator that applies a LIMIT clause to the wrapped table.
 *
 * @param <T> the type of the wrapped table
 */
public final class LimitedTable<T extends Table> implements WrappedTable<T> {
    private final T table;
    private final int limit;

    /**
     * Constructs a new {@code LimitedTable} with the given table and limit.
     *
     * @param table the table to wrap
     * @param limit the maximum number of rows to return
     */
    public LimitedTable(T table, int limit) {
        this.table = table;
        this.limit = limit;
    }

    @Override
    public T origin() {
        return this.table;
    }

    @Override
    public String sql() {
        return String.format("%s LIMIT ?", this.table.sql());
    }

    @Override
    public Bindings bind(Bindings bindings) {
        return this.table.bind(bindings).with(this.limit);
    }
}

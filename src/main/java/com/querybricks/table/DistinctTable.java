package com.querybricks.table;

import com.querybricks.Bindings;

/**
 * A decorator that applies a DISTINCT clause to the wrapped table.
 *
 * @param <T> the type of the wrapped table
 */
public final class DistinctTable<T extends Table> implements WrappedTable<T> {
    private final T table;

    /**
     * Constructs a new {@code DistinctTable} wrapping the specified table.
     *
     * @param table the table to wrap
     */
    public DistinctTable(T table) {
        this.table = table;
    }

    @Override
    public T origin() {
        return this.table;
    }

    @Override
    public String sql() {
        return String.format("DISTINCT %s", this.table.sql());
    }

    @Override
    public Bindings bind(Bindings bindings) {
        return this.table.bind(bindings);
    }
}

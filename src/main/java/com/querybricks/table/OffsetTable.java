package com.querybricks.table;

import com.querybricks.Bindings;

/**
 * A decorator that applies an OFFSET clause to the wrapped table.
 *
 * @param <T> the type of the wrapped table
 */
public final class OffsetTable<T extends Table> implements WrappedTable<T> {
    private final T table;
    private final int offset;

    /**
     * Constructs a new {@code OffsetTable} with the given table and offset.
     *
     * @param table the table to wrap
     * @param offset the number of rows to skip
     */
    public OffsetTable(T table, int offset) {
        this.table = table;
        this.offset = offset;
    }

    @Override
    public T origin() {
        return this.table;
    }

    @Override
    public String sql() {
        return String.format("%s OFFSET ?", this.table.sql());
    }

    @Override
    public Bindings bind(Bindings bindings) {
        return this.table.bind(bindings).with(this.offset);
    }
}

package com.querybricks.table;

public final class LimitedTable<T extends Table> implements WrappedTable<T> {
    private final T table;
    private final int limit;

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
        return String.format("%s LIMIT %d", this.table.sql(), this.limit);
    }
}

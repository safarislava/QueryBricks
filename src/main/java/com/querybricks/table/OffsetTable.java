package com.querybricks.table;

public final class OffsetTable<T extends Table> implements WrappedTable<T> {
    private final T table;
    private final int offset;

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
        return String.format("%s OFFSET %d", this.table.sql(), this.offset);
    }
}

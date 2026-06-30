package com.querybricks.column;

public class Sum<T> implements AggregatedColumn<T> {
    private final Column<T> column;

    public Sum(Column<T> column) {
        this.column = column;
    }

    @Override
    public String sql() {
        return String.format("SUM(%s)", this.column.sql());
    }
}

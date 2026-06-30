package com.querybricks.column;

public class Avg<T> implements AggregatedColumn<T> {
    private final Column<T> column;

    public Avg(Column<T> column) {
        this.column = column;
    }

    @Override
    public String sql() {
        return String.format("AVG(%s)", this.column.sql());
    }
}

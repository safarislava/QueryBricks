package com.querybricks.column;

public class DbColumn<T> implements Column<T> {
    private final String name;

    public DbColumn(String name) {
        this.name = name;
    }

    @Override
    public String sql() {
        return this.name;
    }
}

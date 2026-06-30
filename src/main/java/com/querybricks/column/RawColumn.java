package com.querybricks.column;

public class RawColumn<T> implements UnbindedColumn<T> {
    private final String name;

    public RawColumn(String name) {
        this.name = name;
    }

    @Override
    public String sql() {
        return this.name;
    }
}

package com.querybricks.table;

public final class FakeTable implements Table {
    private final String name;

    public FakeTable(String name) {
        this.name = name;
    }

    @Override
    public String sql() {
        return this.name;
    }
}

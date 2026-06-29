package com.querybricks.column;

public final class FakeColumns implements Columns {
    private final String columns;

    public FakeColumns(String columns) {
        this.columns = columns;
    }

    @Override
    public String sql() {
        return this.columns;
    }
}

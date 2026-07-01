package com.querybricks.table;

import com.querybricks.column.Column;

import java.util.List;

public class FakeFilterableTable implements FilterableTable {
    private final String name;

    public FakeFilterableTable(String name) {
        this.name = name;
    }

    @Override
    public String sql() {
        return this.name;
    }

    @Override
    public List<Column<?>> columns() {
        return List.of();
    }
}

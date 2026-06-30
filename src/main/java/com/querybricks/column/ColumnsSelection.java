package com.querybricks.column;

import java.util.List;
import java.util.stream.Collectors;

public class ColumnsSelection implements Columns {
    private final List<Column<?>> columns;

    public ColumnsSelection(Column<?>... columns) {
        this.columns = List.of(columns);
    }

    @Override
    public String sql() {
        return this.columns.stream().map(Column::sql).collect(Collectors.joining(", "));
    }
}

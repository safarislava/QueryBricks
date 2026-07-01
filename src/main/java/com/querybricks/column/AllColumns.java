package com.querybricks.column;

import com.querybricks.table.Table;

public class AllColumns implements Columns {
    private final Table table;

    public AllColumns(Table table) {
        this.table = table;
    }

    @Override
    public String sql() {
        return "*";
    }

    @Override
    public void processAll(ColumnsProcessor consumer) {
        for (Column<?> column : this.table.columns()) {
            consumer.process(column);
        }
    }
}

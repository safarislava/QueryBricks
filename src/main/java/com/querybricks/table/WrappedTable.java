package com.querybricks.table;

import com.querybricks.column.Column;

import java.util.List;

public interface WrappedTable<T extends Table> extends Table {
    T origin();

    @Override
    default List<Column<?>> columns() {
        return this.origin().columns();
    }
}

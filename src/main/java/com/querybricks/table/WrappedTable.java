package com.querybricks.table;

public interface WrappedTable<T extends Table> extends Table {
    T origin();
}

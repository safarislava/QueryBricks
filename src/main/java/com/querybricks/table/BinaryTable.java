package com.querybricks.table;

public interface BinaryTable<L extends Table, R extends Table> extends Table {
    L left();
    R right();
}

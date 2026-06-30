package com.querybricks.example;

import com.querybricks.column.BindedColumn;
import com.querybricks.table.FilterableTable;

import java.time.Instant;

public interface UsersTable extends FilterableTable {
    BindedColumn<Long> id();
    BindedColumn<String> username();
    BindedColumn<String> status();
    BindedColumn<Instant> createdAt();
}

package com.querybricks.example;

import com.querybricks.column.Column;
import com.querybricks.table.FilterableTable;

public interface UsersTable extends FilterableTable {
    Column<Long> id();
    Column<String> username();
    Column<String> status();
}

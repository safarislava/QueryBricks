package com.querybricks.example;

import com.querybricks.column.Column;
import com.querybricks.table.FilterableTable;

import java.math.BigDecimal;

public interface OrdersTable extends FilterableTable {
    Column<Long> userId();
    Column<BigDecimal> amount();
}

package com.querybricks.example;

import com.querybricks.column.BindedColumn;
import com.querybricks.table.FilterableTable;

import java.math.BigDecimal;

public interface OrdersTable extends FilterableTable {
    BindedColumn<Long> userId();
    BindedColumn<BigDecimal> amount();
}

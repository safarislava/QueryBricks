package com.querybricks.example;

import com.querybricks.column.Column;
import com.querybricks.column.DbColumn;
import com.querybricks.column.TableColumn;

import java.math.BigDecimal;

public class DbOrdersTable implements OrdersTable {
    private final String name;

    public DbOrdersTable(String name) {
        this.name = name;
    }

    @Override
    public String sql() {
        return this.name;
    }

    @Override
    public Column<Long> userId() {
        return new TableColumn<>(this, new DbColumn<>("user_id"));
    }

    @Override
    public Column<BigDecimal> amount() {
        return new TableColumn<>(this, new DbColumn<>("amount"));
    }
}

package com.querybricks.example;

import com.querybricks.column.Column;
import com.querybricks.column.DbColumn;
import com.querybricks.column.TableColumn;

public class DbUsersTable implements UsersTable {
    private final String name;

    public DbUsersTable(String name) {
        this.name = name;
    }

    @Override
    public String sql() {
        return this.name;
    }

    @Override
    public Column<Long> id() {
        return new TableColumn<>(this, new DbColumn<>("id"));
    }

    @Override
    public Column<String> username() {
        return new TableColumn<>(this, new DbColumn<>("username"));
    }

    @Override
    public Column<String> status() {
        return new TableColumn<>(this, new DbColumn<>("status"));
    }
}
